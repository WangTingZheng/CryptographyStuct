package com.wangtingzheng.cryptographystuct.example;

import com.wangtingzheng.cryptographystuct.error.Error;
import com.wangtingzheng.cryptographystuct.error.ErrorList;
import com.wangtingzheng.cryptographystuct.matrix.Matrix;

import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/5/3 21:05
 * @features
 */
public class ErrorExample {
    static final int[][] data = {{1,1,2},{1,2,0},{1,1,3},{1,23,3}}; //一个4*3的整型二维数组
    static final int[][] data1 = {{1,1,2},{1,0,1},{1,1,3}}; //一个3*3的整型二维数组

    public static void main(String[] arg)
    {
        Matrix matrix1 = new Matrix(data);  //用数组初始化矩阵
        Matrix matrix2 = new Matrix(data1); //用数组初始化矩阵
        matrix1.addWith(matrix2); //把矩阵1和矩阵2相加，结果必然出错，因为它们的大小不同
        ErrorList errorList = matrix1.getErrorList(); //获取执行后的错误列表对象
        List<Error> errors = errorList.getEnableError(); //获取执行后的已经被触发的错误列表
        for(Error error: errors) //遍历错误列表
        {
            if(error.id == Matrix.ErrorNumber.MatrixSizeNotMatch) //如果发现有被触发的错误中有一个是叫MatrixSizeNotMatch的
            {
                System.out.println("we got an MatrixSizeNotMatch error!");//打印提示
            }
        }
    }
}
