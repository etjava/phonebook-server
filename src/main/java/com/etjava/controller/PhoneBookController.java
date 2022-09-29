package com.etjava.controller;

import com.etjava.bean.PhoneBook;
import com.etjava.bean.R;
import com.etjava.service.PhoneBookService;
import com.etjava.util.PinYinUtil;
import com.etjava.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/phoneBook")
public class PhoneBookController {

    private Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    @Autowired
    private PhoneBookService phoneBookService;

    @RequestMapping("/delete")
    public R delete(Integer id) throws Exception{
        Integer result = phoneBookService.delete(id);
        if(result>0){
            logger.info("删除 信息成功 id="+id);
            return R.ok();
        }else{
            logger.error("删除 信息失败 id="+id);
            return R.error(-1, "删除失败");
        }
    }


    @RequestMapping("/findById")
    public R findById(Integer id) throws Exception{
        PhoneBook phoneBook = phoneBookService.findById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("phoneBook",phoneBook);
        return R.ok(map);
    }

    /**
     * 新增或修改
     *
     * @param phoneBook
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public R save(@RequestBody PhoneBook phoneBook) throws Exception {

        int resultTotal = 0;
        String initial = String.valueOf(PinYinUtil.getPinYin(phoneBook.getName()).charAt(0)).toUpperCase();
        if (StringUtil.isAlpha(initial)) {
            phoneBook.setInitial(initial);
        } else {
            phoneBook.setInitial("#");
        }
        if (phoneBook.getId() == null) {
            resultTotal = phoneBookService.add(phoneBook);
        } else {
            resultTotal = phoneBookService.update(phoneBook);
        }
        if (resultTotal > 0) {
            logger.info("添加/修改 信息成功 "+phoneBook);
            return R.ok();
        } else {
            logger.error("添加信息失败 "+phoneBook);
            return R.error(-1, "保存失败");
        }
    }

    /*
    查询所有电话簿信息
     */
    @RequestMapping("/all")
    public R loadAll() throws Exception {
        // 需要有序map
        Map<String, Object> map = new LinkedHashMap<>();
        char[] letter = new char[27];
        for (int i = 0; i < 26; i++) {
            letter[i] = (char) (65 + i);
        }
        System.out.println(letter.length);
        letter[26] = '#'; // 下标26 表示第27个元素 因为下标是从0开始


        for (int i = 0; i < letter.length; i++) {
            String s = String.valueOf(letter[i]);
            List<PhoneBook> phoneBooks = phoneBookService.listByInitial(s);
            if (phoneBooks.size() > 0) {
                map.put(s, phoneBooks);
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", map);
        return R.ok(resultMap);
    }
}
