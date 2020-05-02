package com.wangtingzheng.cryptographystuct.matrix;

/**
 * @author WangTingZheng
 * @date 2020/4/30 21:58
 * @features
 */
public class MatrixClass {

    public static class Operation
    {
        public static int add = 1;
        public static int minus = 2;
        public static int multiply = 3;
    }

    public static class Type
    {
        public static int none = 0;
        public static int IntMatirx =1;
        public static int CharMatrix = 2;
    }
    public int type = Type.none;

}
