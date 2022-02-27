package com.jiangxia.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxia.eduservice.config.SubjectExcelListenerConfig;
import com.jiangxia.eduservice.entity.EduSubject;
import com.jiangxia.eduservice.entity.excel.SubjectData;
import com.jiangxia.eduservice.mapper.EduSubjectMapper;
import com.jiangxia.eduservice.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jiangxia
 * @date 2022年02月27日 19:55
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListenerConfig(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
