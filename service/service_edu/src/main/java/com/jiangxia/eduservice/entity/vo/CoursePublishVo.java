package com.jiangxia.eduservice.entity.vo;

import lombok.Data;

/**
 * @author jiangxia
 * @date 2022年03月06日 11:09
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
