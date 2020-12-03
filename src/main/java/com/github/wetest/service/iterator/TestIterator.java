package com.github.wetest.service.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author JZWen
 * @date 2020/12/2
 */
public class TestIterator {


    public static void main(String[] args) {
        MsgCategoryInfo msgCategoryInfo = new MsgCategoryInfo();
        msgCategoryInfo.setCategoryName("name");
        MsgTypeInfo msgTypeInfo = new MsgTypeInfo();
        msgTypeInfo.setType("1");
        MsgTypeInfo msgTypeInfo1 = new MsgTypeInfo();
        msgTypeInfo1.setType("2");
        MsgTypeInfo msgTypeInfo2 = new MsgTypeInfo();
        msgTypeInfo2.setType("3");
        List<MsgTypeInfo> list = new ArrayList<>();
        list.add(msgTypeInfo);
        list.add(msgTypeInfo1);
        list.add(msgTypeInfo2);

        Iterator<MsgTypeInfo> iter = list.iterator();
        while (iter.hasNext()) {
            MsgTypeInfo msgTypeInfo3 = iter.next();
            if (msgTypeInfo3.getType().equals("2")) {
                list.remove(msgTypeInfo3);
            }
        }

        list.forEach(a -> {
            System.out.println(a.getType());
        });

    }

}
