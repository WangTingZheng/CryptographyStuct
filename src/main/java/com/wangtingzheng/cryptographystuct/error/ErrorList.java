package com.wangtingzheng.cryptographystuct.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/5/3 18:02
 * @features
 */
public class ErrorList {
    public List<Error> list = new ArrayList<>(); //错误列表

    /**
     * 传入一个错误来获得错误列表的对象
     * @param error 传入的第一个错误对象
     */
    public ErrorList(Error error) {
        list.add(error);
    }

    /**
     * 获取到对象中的错误列表
     * @return 错误列表
     */
    public List<Error> getList() {
        return list;
    }

    /**
     * 设置对象中的错误列表,存在问题，暂时不公开
     * @param list 要设置的错误列表
     */
    private void setList(List<Error> list) {
        this.list = list;
    }

    /**
     * 向错误列表中添加一个错误，如果遇到相同的错误，则不添加
     * 相同的错误是指来自同一个类，id也一样的错误，也就是belongClass和id都相等
     * @param error 要添加的错误
     */
    public void addError(Error error)
    {
        boolean have = false;
        for(Error errors: list)
        {
            if(errors.belongClass == error.belongClass && errors.id == error.id)
            {
                have = true;
            }
        }
        if(!have)
            list.add(error);
    }

    /**
     * 向错误列表中添加一个错误，如果遇到相同的错误，则覆盖
     * 相同的错误是指来自同一个类，id也一样的错误，也就是belongClass和id都相等
     * @param error 要添加的错误
     */
    public void addErrorUpdate(Error error)
    {
        int number = 0;
        for(Error errors: list)
        {
            if(errors.belongClass == error.belongClass && errors.id == error.id)
            {
                list.remove(number);
                number ++;
            }
        }
        list.add(error);
    }

    /**
     * 根据id来执行一个已经触发的错误
     * 前提是你得知道列表中的所有错误都来自同一个类
     * 否则可能会出现触发不想触发的错误处理函数（来自不同类的错误的id可能一样）
     * @param id 要执行处理函数的错误的id
     */
    public void doErrorReportAccordingId(int id)
    {
        for(Error error: list)
        {
            if(error.id == id && error.enable)
                error.errorReport();
        }
    }

    /**
     * 根据来自的类和id来执行一个已经触发的错误
     * 被执行处理函数的错误是唯一的
     * @param thisClass 来自的类，使用ClassName.getClass()来获取
     * @param id 错误的id
     */
    public void doErrorReportAcrroding(Class thisClass, int id)
    {
        for(Error error: list)
        {
            if(error.belongClass == thisClass && error.id ==id && error.enable)
                error.errorReport();
        }
    }

    /**
     * 执行列表中所有被触发的错误的处理函数
     */
    public void doErrorReport()
    {
        for(Error error: list)
        {
            if(error.enable)
                error.errorReport();
        }
    }

    /**
     * 根据所属的类和id触发一个错误
     * 并且执行错误处理函数
     * @param thisClass 错误所属的类，使用ClassName.getClass()来获取
     * @param id 错误的id
     */
    public void enableError(Class thisClass, int id)
    {
        for(Error error: list)
        {
            if(error.belongClass == thisClass && error.id ==id)
            {
                error.enable = true;
                error.errorReport();
            }
        }
    }

    /**
     * 根据id触发一个错误
     * 并且执行错误处理函数
     * 前提是你得知道列表中的所有错误都来自同一个类
     * 否则可能会出现触发不想触发的错误处理函数（来自不同类的错误的id可能一样）
     * @param id 错误的id
     */
    public void enableError(int id)
    {
        for(Error error: list)
        {
            if(error.id ==id)
            {
                error.enable = true;
                error.errorReport();
            }
        }
    }


    /**
     * 根据所属的类和id触发一个错误
     * @param thisClass 错误所属的类，使用ClassName.getClass()来获取
     * @param id 错误的id
     */
    public void enableErrorWithoutDoReport(Class thisClass, int id)
    {
        for(Error error: list)
        {
            if(error.belongClass == thisClass && error.id ==id)
            {
                error.enable = true;
            }
        }
    }

    /**
     * 根据所属的类和id触发一个错误
     * 前提是你得知道列表中的所有错误都来自同一个类
     * 否则可能会出现触发不想触发的错误处理函数（来自不同类的错误的id可能一样）
     * @param id 错误的id
     */
    public void enableErrorWithoutDoReport(int id)
    {
        for(Error error: list)
        {
            if(error.id ==id)
            {
                error.enable = true;
            }
        }
    }


    /**
     * 取消触发一个错误
     * @param thisClass 错误所属的类，使用ClassName.getClass()来获取
     * @param id 错误的id
     */
    public void disable(Class thisClass, int id)
    {
        for(Error error: list)
        {
            if(error.belongClass == thisClass && error.id ==id)
            {
                error.enable = false;
            }
        }
    }


    /**
     * 取消触发一个错误
     * 前提是你得知道列表中的所有错误都来自同一个类
     * 否则可能会出现触发不想触发的错误处理函数（来自不同类的错误的id可能一样）
     * @param id 错误的id
     */
    public void disable(int id)
    {
        for(Error error: list)
        {
            if(error.id ==id)
            {
                error.enable = false;
            }
        }
    }


    /**
     * 获取本列表中已经被触发的错误
     * @return 已经被触发过的错误的列表
     */
    public List<Error> getEnableError()
    {
        List<Error> res = new ArrayList<>();
        for(Error error: list)
        {
            if(error.enable)
            {
               res.add(error);
            }
        }
        return res;
    }


    /**
     * 获取本列表中已经没有被触发的错误
     * @return 已经没有被触发过的错误的列表
     */
    public List<Error> getDisableError()
    {
        List<Error> res = new ArrayList<>();
        for(Error error: list)
        {
            if(!error.enable)
            {
                res.add(error);
            }
        }
        return res;
    }
}
