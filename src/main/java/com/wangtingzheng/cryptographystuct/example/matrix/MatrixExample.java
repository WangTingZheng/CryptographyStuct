package com.wangtingzheng.cryptographystuct.example.matrix;

import com.wangtingzheng.cryptographystuct.matrix.Matrix;

/**
 * @author WangTingZheng
 * @date 2020/5/4 13:41
 * @features
 */
public class MatrixExample {
    public static void main(String[] args)
    {
        int[][] data1 = {{1,2,3},{3,2,3},{5,4,8}};
        int[][] data2 = {{2,3,2},{3,8,7},{9,8,3}};
        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);
        System.out.println("矩阵1加上矩阵2的结果是:");
        matrix1.addWith(matrix2).printMatrix();
        System.out.println("矩阵1减去矩阵2的结果是:");
        matrix1.minusWith(matrix2).printMatrix();
        System.out.println("矩阵1乘以矩阵2的结果是:");
        matrix1.multiplyWith(matrix2).printMatrix();
        System.out.println("矩阵1取逆的结果是:");
        matrix1.inverse().printMatrix();
        System.out.println("矩阵2取逆的结果是:");
        matrix1.transpose().printMatrix();

        matrix1 = matrix1.toCharMatrix();
        matrix2 = matrix2.toCharMatrix();

        System.out.println(matrix1.checkIsLetter()?"矩阵1的元素全是字母":"矩阵1有不是字母的元素");
        System.out.println(matrix2.checkIsLetter()?"矩阵2的元素全是字母":"矩阵2有不是字母的元素");
    }
}
