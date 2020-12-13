package com.note.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.note.entities.Diary;
import com.note.req.DiaryReq;
import com.note.util.base.Result;

/**
 * @className: IDiaryService
 * @description: 日记接口
 * @author: lgp
 * @date: 2020/12/5 11:19
 * @version: 1.0
 */
public interface IDiaryService extends IService<Diary> {
    Result serch(long page,long size,DiaryReq req);

    Result updateDiaryById(int id,Diary diary);
}
