package com.note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.note.entities.Diary;
import com.note.mapper.DiaryMapper;
import com.note.req.DiaryReq;
import com.note.service.IDiaryService;
import com.note.util.base.Page;
import com.note.util.base.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @className: DiaryServiceImp
 * @description: 日记接口实现类
 * @author: lgp
 * @date: 2020/12/5 11:21
 * @version: 1.0
 */
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements IDiaryService {

    @Override
    public Result serch(long page,long size,DiaryReq req) {
        QueryWrapper query = new QueryWrapper();
        if (req!=null){
            if(StringUtils.isNotBlank(req.getTitle())){
                query.like("title",req.getTitle());
            }
            if (StringUtils.isNotBlank(req.getEspecially())) {
                query.like("especially",req.getEspecially());
            }
            if (StringUtils.isNotBlank(req.getMood())) {
                query.eq("mood",req.getMood());
            }
            if (req.getCreateDate() != null) {
                query.eq("create_date",req.getCreateDate());
            }
        }
        IPage<Diary> diaryPage = new Page<>(page, size);

        IPage<Diary> data = baseMapper.selectPage(diaryPage, query);

        return Result.ok(data);
    }

    @Override
    public Result updateDiaryById(int id, Diary diary) {
        if (diary.getId() == null) {
            diary.setId(id);
        }
        int i = baseMapper.updateById(diary);
        if (i<1) {
            return Result.error("修改日记失败");
        }
        return Result.ok();
    }


}
