package com.etjava.mapper;

import com.etjava.bean.PhoneBook;

import java.util.List;

/**
 * 电话簿Mapper
 */
public interface PhoneBookMapper {

    /**
     * 根据姓名首字母查询电话簿
     * @param initial 姓名首字母
     * @return
     */
    List<PhoneBook> listByInitial(String initial);


    Integer add(PhoneBook phoneBook);

    PhoneBook findById(Integer id);

    Integer update(PhoneBook phoneBook);

    Integer delete(Integer id);

}
