package com.etjava.service;

import com.etjava.bean.PhoneBook;

import java.util.List;

public interface PhoneBookService {

    List<PhoneBook> listByInitial(String initial);

    Integer add(PhoneBook phoneBook);

    PhoneBook findById(Integer id);

    Integer update(PhoneBook phoneBook);

    Integer delete(Integer id);
}
