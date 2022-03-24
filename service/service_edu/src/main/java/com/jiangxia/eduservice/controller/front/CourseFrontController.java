package com.jiangxia.eduservice.controller.front;

import com.jiangxia.commonutils.ResultData;
import com.jiangxia.eduservice.entity.EduCourse;
import com.jiangxia.eduservice.entity.chapter.ChapterVo;
import com.jiangxia.eduservice.entity.frontvo.CourseFrontVo;
import com.jiangxia.eduservice.entity.frontvo.CourseWebVo;
import com.jiangxia.eduservice.service.EduChapterService;
import com.jiangxia.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author jiangxia
 * @date 2022年03月17日 15:20
 * desc:用户前端（学生）获取课程信息
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {
    //课程服务
    @Autowired
    private EduCourseService courseService;

    //课程小节服务
    @Autowired
    private EduChapterService chapterService;

//    条件分页查询课程信息
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public ResultData getFrontCourseList(@PathVariable long page,@PathVariable long limit,@RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return ResultData.ok().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public ResultData getFrontCourseInfo(@PathVariable String courseId) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        return ResultData.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }
}
