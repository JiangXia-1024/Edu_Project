package com.jiangxia.canal.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author jiangxia
 * @date 2022年03月24日 10:26
 * desc:阿里开源的框架Canal，可以很方便地
 * 同步数据库的增量数据到其他的存储应用,比如：
 * Elastic Search、HBase、Redis等等。
 * 用途是基于 MySQL 数据库增量日志解析，提供增量数据订阅和消费。
 */
@Component
public class CanalClient {
    //sql队列
    private Queue<String> SQL_QUEUE = new ConcurrentLinkedDeque<>();
    //@Resource是JSR-250提供的，它是Java标准，绝大部分框架都支持。
    // 除此之外，有些场景使用@Autowired无法满足的要求，改成@Resource却能解决问题。
    // @Autowired默认按byType自动装配，而@Resource默认byName自动装配。
    @Resource
    private DataSource dataSource;

    /**
     * canal入库方法
     */
    public void run(){
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("192.168.44.132",
                11111), "example", "", "");
        int batchSize = 1000;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            try {
                while (true){
                    //尝试从master获取batchSize条记录，有多少取多少
                    Message message = connector.getWithoutAck(batchSize);
                    long batchID = message.getId();
                    int size = message.getEntries().size();
                    if(batchID==-1||size ==0){
                        Thread.sleep(1000);
                    }else{
                        dataHandle(message.getEntries());//处理数据sql
                    }
                    connector.ack(batchID);
                    //当队列里面堆积的sql大于一定数值的时候就执行
                    if(SQL_QUEUE.size()>=1){
                        executeQueueSql();
                    }
                }
            }
            catch (InterruptedException e) {
               e.printStackTrace();
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
    }

    /** 
     * @description: 模拟执行队列中的sql语句
     * @param:  
     * @return:  
     * @author:江夏
     * @date: 2022/3/24 16:24
     */ 
    private void executeQueueSql() {
        int size = SQL_QUEUE.size();
        for (int i = 0; i < size; i++) {
            String strSql = SQL_QUEUE.poll();//队列中取值
            System.out.println("[Sql]---------->"+strSql);
            this.execute(strSql);
        }
    }

    /** 
     * @description: 执行sql 数据写入库
     * @param: strSql 
     * @return:  
     * @author:江夏
     * @date: 2022/3/24 16:26
     */ 
    private void execute(String strSql) {
        Connection connection = null;
        try {
            if (null==strSql){
                connection = dataSource.getConnection();//建立连接
                QueryRunner queryRunner = new QueryRunner();
                int row = queryRunner.execute(connection,strSql);
                System.out.println("执行了："+row);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtils.closeQuietly(connection);//关闭连接
        }
    }

    /*
     *
     * @author jiangxia
     * @date 2022/3/24 16:14
     * @param No such property: code for class: Script1
     * @return No such property: code for class: Script1
     * @description:数据处理
     */
    public void dataHandle(List<Entry> entries) throws InvalidProtocolBufferException{
        //遍历
        for(Entry entry : entries){
            if(EntryType.ROWDATA==entry.getEntryType()){
                RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
                EventType eventType = rowChange.getEventType();
                if(eventType==EventType.DELETE){
                    saveDeleteSql(entry);//删除
                }else if(eventType==EventType.UPDATE){
                    saveUpdateSql(entry);
                } else if (eventType == EventType.INSERT) {
                    saveInsertSql(entry);
                }
            }
        }
    }

    /** 
     * @description: 插入语句
     * @param: entry 
     * @return:  
     * @author:江夏
     * @date: 2022/3/24 16:20
     */ 
    private void saveInsertSql(Entry entry) {
        try {
            RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
            List<RowData> rowDatasList = rowChange.getRowDatasList();
            for (RowData rowData : rowDatasList) {
                List<Column> columnList = rowData.getAfterColumnsList();
                StringBuffer sql = new StringBuffer("insert into " + entry.getHeader().getTableName() + " (");
                for (int i = 0; i < columnList.size(); i++) {
                    sql.append(columnList.get(i).getName());
                    if (i != columnList.size() - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") VALUES (");
                for (int i = 0; i < columnList.size(); i++) {
                    sql.append("'" + columnList.get(i).getValue() + "'");
                    if (i != columnList.size() - 1) {
                        sql.append(",");
                    }
                }
                sql.append(")");
                SQL_QUEUE.add(sql.toString());
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    /** 
     * @description: 更新语句 
     * @param: entry 
     * @return:  
     * @author:江夏
     * @date: 2022/3/24 16:20
     */ 
    private void saveUpdateSql(Entry entry) {
        try {
            RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
            List<RowData> rowDatasList = rowChange.getRowDatasList();
            for (RowData rowData : rowDatasList) {
                List<Column> newColumnList = rowData.getAfterColumnsList();
                StringBuffer sql = new StringBuffer("update " + entry.getHeader().getTableName() + " set ");
                for (int i = 0; i < newColumnList.size(); i++) {
                    sql.append(" " + newColumnList.get(i).getName()
                            + " = '" + newColumnList.get(i).getValue() + "'");
                    if (i != newColumnList.size() - 1) {
                        sql.append(",");
                    }
                }
                sql.append(" where ");
                List<Column> oldColumnList = rowData.getBeforeColumnsList();
                SqlMethod(sql,oldColumnList);
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
    
    /** 
     * @description: 删除语句
     * @param: entry 
     * @return:  
     * @author:江夏
     * @date: 2022/3/24 16:20
     */ 
    private void saveDeleteSql(Entry entry) {
        try {
            RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
            List<RowData> rowDatasList = rowChange.getRowDatasList();
            for (RowData rowData : rowDatasList) {
                List<Column> columnList = rowData.getBeforeColumnsList();
                StringBuffer sql = new StringBuffer("delete from " + entry.getHeader().getTableName() + " where ");
                SqlMethod(sql,columnList);
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description: 抽取公用部分
     * @param: null
     * @return:
     * @author:江夏
     * @date: 2022/3/24 16:30
     */
    public void SqlMethod(StringBuffer sql, List<Column> list){
        for (Column column : list) {
            if (column.getIsKey()) {
                //暂时只支持单一主键
                sql.append(column.getName() + "=" + column.getValue());
                break;
            }
        }
        SQL_QUEUE.add(sql.toString());
    }
}
