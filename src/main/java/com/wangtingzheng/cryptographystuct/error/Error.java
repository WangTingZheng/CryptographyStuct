package com.wangtingzheng.cryptographystuct.error;

import com.wangtingzheng.cryptographystuct.example.error.ErrorSetExample;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/5/3 17:58
 * @features
 */
public abstract class Error {
    public int id; // 错误的id，在一个类中是唯一的
    public Class belongClass; //这个错误属于的类
    private boolean enable; //这个错误是否被触发
    private List<ErrorList> listOfErrorList = new ArrayList<>(); //这个错误属于的错误列表的列表

    public Error(int id, Class belongClass, ErrorList errorList) {
        this.belongClass = belongClass;
        this.id = id;
        enable = false;
        addToNewList(errorList);
    }


    public void enable() {
        this.enable = true;
        errorReport();
        for(ErrorList errorList: listOfErrorList)
            errorList.update(this);
    }

    public void enableDoNotReport()
    {
        this.enable = true;
        for(ErrorList errorList: listOfErrorList)
            errorList.update(this);
    }

    public void disable()
    {
        this.enable = false;
        for(ErrorList errorList: listOfErrorList)
            errorList.update(this);
    }


    public boolean isEnable() {
        return enable;
    }

    public void addToNewList(ErrorList errorList)
    {
        if(!this.listOfErrorList.contains(errorList))
            this.listOfErrorList.add(errorList);
        if(!errorList.getList().contains(this))
            errorList.addError(this); //把本错误对象添加进错误列表对象中
    }

    public boolean errorListContain(ErrorList errorList)
    {
        return this.listOfErrorList.contains(errorList);
    }


    public List<ErrorList> getListOfErrorList() {
        return listOfErrorList;
    }

    public abstract void errorReport(); //当这个类被触发时应该执行的函数
}
