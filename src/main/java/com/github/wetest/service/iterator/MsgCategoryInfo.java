package com.github.wetest.service.iterator;

import java.util.Date;
import java.util.List;

/**
 * @author JZWen
 * @date 2020/12/2
 */
public class MsgCategoryInfo {

    private String categoryName;

    private List<MsgTypeInfo> msgTypeList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<MsgTypeInfo> getMsgTypeList() {
        return msgTypeList;
    }

    public void setMsgTypeList(List<MsgTypeInfo> msgTypeList) {
        this.msgTypeList = msgTypeList;
    }
}