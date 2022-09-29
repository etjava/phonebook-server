package com.etjava.service.impl;

import com.etjava.bean.PhoneBook;
import com.etjava.mapper.PhoneBookMapper;
import com.etjava.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 电话簿service实现类
 */
@Service("phoneBookService")
public class PhoneBookServiceImpl implements PhoneBookService {

    @Autowired
    private PhoneBookMapper phoneBookMapper;

    @Override
    public List<PhoneBook> listByInitial(String initial) {
        return phoneBookMapper.listByInitial(initial);
    }

    @Override
    public Integer add(PhoneBook phoneBook) {
        return phoneBookMapper.add(phoneBook);
    }

    @Override
    public PhoneBook findById(Integer id) {
        return phoneBookMapper.findById(id);
    }

    @Override
    public Integer update(PhoneBook phoneBook) {
        return phoneBookMapper.update(phoneBook);
    }

    @Override
    public Integer delete(Integer id) {
        return phoneBookMapper.delete(id);
    }
}
