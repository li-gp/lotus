package com.note.controller;

import com.note.entities.Bill;
import com.note.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BillController
 * @Description: 账单控制
 * @Author: lgp
 * @Date: 2020/9/24 18:53
 * @Version: 1.0
 */
@RestController
public class BillController {

    private BillMapper billMapper;

    @Autowired
    public void setBillMapper(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    @GetMapping("/bill/{id}")
    public Bill getBill(@PathVariable("id") Integer id) {
        return billMapper.getBill(id);
    }

}
