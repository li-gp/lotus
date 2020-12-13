package com.note.controller;

import com.note.entities.Diary;
import com.note.req.DiaryReq;
import com.note.service.IDiaryService;
import com.note.util.base.Result;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @className: DiaryController
 * @description: 日记控制层
 * @author: lgp
 * @date: 2020/12/5 14:41
 * @version: 1.0
 */
@RequestMapping("diary")
@RestController
public class DiaryController {
    Logger logger = LoggerFactory.getLogger(getClass());

    private IDiaryService diaryService;

    @Autowired
    public void setDiaryService(IDiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/search/{page}/{size}")
    public Result search(@PathVariable("page") long page,@PathVariable("size") long size,@RequestBody DiaryReq req) {
        logger.info("日记列表，page={}, size={}",page,size);
        //ctrl + alt +点击 进入实现方法 ctrl+alt+左方向
        return diaryService.serch(page,size,req);
    }

    @PostMapping("/addDiary")
    public Result addDiary(@RequestBody Diary diary) {
        boolean b = diaryService.save(diary);
        if (b) {
            return Result.ok();
        }
        return Result.error("新增日记失败");
    }

    @Delete("/deleteDiary")
    public Result deleteDiary(@RequestParam int id) {
        boolean b = diaryService.removeById(id);
        if (b) {
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    @GetMapping("/getOneDiary/{id}")
    public Result getOneDiary(@PathVariable(value = "id") int id) {
        Diary diary = diaryService.getById(id);
        return Result.ok(diary);
    }

    @PutMapping("/updateDiary/{id}")
    public Result updateDiary(@PathVariable int id, @RequestBody Diary diary) {
        // id其实可以不传，diary里面有id
        return diaryService.updateDiaryById(id,diary);
    }

}
