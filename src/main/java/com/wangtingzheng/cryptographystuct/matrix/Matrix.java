package com.wangtingzheng.cryptographystuct.matrix;

/**
 * @author WangTingZheng
 * @date 2020/5/1 18:06
 * @features
 */
public class Matrix {
    public IntMatrix intMatrix;
    public CharMatrix charMatrix;
    public int type;

    /**
     * 创建一个整型矩阵，元素默认值可以自定义
     * @param row 行数
     * @param column 列数
     * @param defalutInt 每个元素的默认值
     */
    public Matrix(int row, int column, int defalutInt) {
        type = MatrixClass.Type.IntMatirx;
        intMatrix = new IntMatrix(row, column, defalutInt);
    }

    /**
     * 创建一个字符矩阵，元素默认值可以自定义
     * @param row 行数
     * @param column 列数
     * @param defalutChar 每个元素的默认值
     */
    public Matrix(int row, int column, char defalutChar)
    {
        type = MatrixClass.Type.CharMatrix;
        charMatrix = new CharMatrix(row, column, defalutChar);
    }

    /**
     * 用一个整型二维数组来新建一个矩阵对象
     * @param intData 要传入的整型二维矩阵
     */
    public Matrix(int[][] intData) {
        type = MatrixClass.Type.IntMatirx;
        intMatrix = new IntMatrix(intData);
    }

    /**
     * 用一个字符型二维数组来新建一个矩阵对象
     * @param charData 要传入的整型二维矩阵
     */
    public Matrix(char[][] charData)
    {
        type = MatrixClass.Type.CharMatrix;
        charMatrix = new CharMatrix(charData);
    }

    /**
     * 用一个字符串创建一个字符型的一维矩阵
     * @param data 传入的字符串
     */
    public Matrix(String data)
    {
        type = MatrixClass.Type.CharMatrix;
        charMatrix = new CharMatrix(data);
    }

    /**
     * 获得具体的矩阵
     * @param <V> 一个泛型，有可能是IntMatix，也有可能的CharMatrix，可以根据type的值来判断
     * @return 本矩阵对应的IntMatrix或CharMatrix
     */
    public <V> V getMatrix()
    {
        if(type == MatrixClass.Type.IntMatirx)
        {
            return  (V) intMatrix;
        }
        else if(type == MatrixClass.Type.CharMatrix)
        {
            return (V) charMatrix;
        }
        return null;
    }

    /**
     * 设置本矩阵的子矩形为IntMatrix，并赋值
     * @param intMatrix 要传入的int型矩阵
     */
    public void setIntMatrix(IntMatrix intMatrix) {
        type = MatrixClass.Type.IntMatirx;
        this.intMatrix = intMatrix;
    }

    /**
     * 设置本矩阵的子矩形为charMatrix，并赋值
     * @param charMatrix 要传入的char型矩阵
     */
    public void setCharMatrix(CharMatrix charMatrix) {
        type = MatrixClass.Type.CharMatrix;
        this.charMatrix = charMatrix;
    }
}
