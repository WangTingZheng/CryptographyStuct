package com.wangtingzheng.cryptographystuct.error;

/**
 * @author WangTingZheng
 * @date 2020/5/3 22:59
 * @features
 */
public abstract class ErrorTemplate {
    public ErrorList errorList = new ErrorList();  //错误列表，存储程序运行时出现过的错误

    public ErrorTemplate() {
        errorInit();
    }

    /**
     * 错误设定初始化函数，设定了在本类中会出现的错误的id和发生时执行的函数
     */
    public abstract void errorInit();


    /**
     * 错误数静态内部类，用来存放错误id
     */
    public static class ErrorNumber
    {

    }

    public  ErrorList getErrorList()
    {
        return errorList;
    }
}
