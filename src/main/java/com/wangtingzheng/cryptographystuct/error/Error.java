package com.wangtingzheng.cryptographystuct.error;

/**
 * @author WangTingZheng
 * @date 2020/5/3 17:58
 * @features
 */
public abstract class Error {
    public int id; // 错误的id，在一个类中是唯一的
    public Class belongClass; //这个错误属于的类
    public boolean enable; //这个错误是否被触发
    public Error(int id, Class belongClass) {
        this.belongClass = belongClass;
        this.id = id;
        enable = false;
    }

    public abstract void errorReport(); //当这个类被触发时应该执行的函数
}
