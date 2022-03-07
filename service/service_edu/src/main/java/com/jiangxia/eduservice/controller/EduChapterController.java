package com.jiangxia.eduservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.jiangxia.commonutils.ResultData;
import com.jiangxia.eduservice.entity.EduChapter;
import com.jiangxia.eduservice.entity.chapter.ChapterVo;
import com.jiangxia.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年03月02日 20:30
 * desc:课程
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    //课程大纲列表,根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public ResultData getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return ResultData.ok().data("allChapterVideo",list);
    }

    //添加章节
    @PostMapping("addChapter")
    public ResultData addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return ResultData.ok();
    }

    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public ResultData getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return ResultData.ok().data("chapter",eduChapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public ResultData updateChapter(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return ResultData.ok();
    }

    //删除的方法
    @DeleteMapping("{chapterId}")
    public ResultData deleteChapter(@PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag) {
            return ResultData.ok();
        } else {
            return ResultData.error();
        }

    }
}
