package com.note.mapper;

import com.note.entities.Bill;
import org.springframework.stereotype.Repository;

/**
 * @className: BillMapper
 * @description: 账单接口
 * @author: lgp
 * @date: 2020/9/24 18:50
 * @version: 1.0
 */
@Repository
public interface BillMapper {


    /**
     * 账单
     *
     * @param id 账单id
     * @return Bill 账单
     */
    Bill getBill(Integer id);
}
