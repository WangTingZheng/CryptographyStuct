package com.wangtingzheng.cryptographystuct.matrix;

import Jama.Matrix;

import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/4/30 22:00
 * @features
 */
public class IntMatrix extends MatrixClass {

    public int[][] intData;

    /**
     * 使用int型二维数组创建Matrix对象
     * @param intData 要输入的int型二维数组
     */
    public IntMatrix(int[][] intData) {
        type = Type.IntMatirx;
        this.intData = intData;
    }

    /**
     * 创建一个空的(元素值为0)的Matrix对象
     * @param row Matrix的行数
     * @param column Matrix的列数
     * @param defalutInt 元素的默认值
     */
    public IntMatrix(int row, int column, int defalutInt) {
        type = Type.IntMatirx;
        int[][] data = new int[row][column];
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
               data[i][j] = defalutInt;
            }
        }
        this.intData = data;
    }

    /**
     * 使用Interger列表创建Matrix对象
     * @param integers 要输入的Integer型列表
     */
    public IntMatrix(List<Integer> integers)
    {
        type = Type.IntMatirx;
        int[][] data = new int[1][integers.size()];
        for(int i = 0; i< integers.size();i++)
        {
            data[0][i] = integers.get(i);
        }
        this.intData = data;
    }

    /**
     * 检查一个矩阵是否满足运算的一些前提条件
     * @param intMatrix 要检查的矩阵
     * @param action 要采取的运算，在Matrix中有定义
     * @return 如果满足，返回true，否则返回false
     */
    public boolean check(IntMatrix intMatrix, int action)
    {
        int thisRow = this.getRow();
        int thisColumn = this.getColumn();
        int addedRow = intMatrix.getRow();
        int addedColumn = intMatrix.getColumn();

        if(action == Operation.add || action == Operation.minus)
        {
            if(thisRow != addedRow || thisColumn != addedColumn)
            {
                System.out.println("Two matrix have different row number or column number!");
                return false;
            }
        }
        else if(action == Operation.multiply)
        {
            if(thisColumn != addedRow)
            {
                System.out.println("First matrix column not equals with second matrix row");
                return false;
            }
        }
        return true;
    }

    /**
     * 将本矩阵与另外一个矩阵相加，返回一个结果矩阵
     * @param intMatrix 要与本矩阵相加的矩阵
     * @return 结果矩阵
     */
    public IntMatrix addWith(IntMatrix intMatrix)
    {
        if(check(intMatrix, Operation.add))
        {
            IntMatrix matrixRes = new IntMatrix(getRow(), getColumn(), 0);

            for(int i=0;i<getRow();i++)
            {
                for(int j =0;j<getColumn();j++)
                {
                    matrixRes.intData[i][j] = this.intData[i][j]+intMatrix.intData[i][j];
                }
            }
            return matrixRes;
        }
        return null;

    }

    /**
     * 把本矩阵减去一个矩阵
     * @param intMatrix 本矩阵减去的矩阵
     * @return 减的结果矩阵
     */
    public IntMatrix minusWith(IntMatrix intMatrix)
    {
        if(check(intMatrix, Operation.add))
        {
            IntMatrix matrixRes = new IntMatrix(getRow(), getColumn(), 0);

            for(int i=0;i<getRow();i++)
            {
                for(int j =0;j<getColumn();j++)
                {
                    matrixRes.intData[i][j] = this.intData[i][j]-intMatrix.intData[i][j];
                }
            }
            return matrixRes;
        }
        return null;
    }

    /**
     * 返回本矩阵与另外一个矩阵相乘的结果
     * @param intMatrix 要与本矩阵相乘的矩阵
     * @return 乘法的结果
     */
    public IntMatrix multiplyWith(IntMatrix intMatrix)
    {
        if(check(intMatrix, Operation.multiply))
        {
            IntMatrix res  = new IntMatrix(getRow(), intMatrix.getColumn(), 0);
            IntMatrix tras = intMatrix.transpose();
            int a = 0;
            int b = 0;
            for(int i =0; i < getRow();i++)
            {
                for(int j =0; j < intMatrix.getColumn();j++)
                {
                    int result = 0;
                    for(int z = 0; z < getColumn();z++)
                    {
                        result += intData[a][z]*tras.intData[b][z];
                    }
                    res.intData[i][j] = result;
                    b++;
                }
                a++;
                b=0;
            }
            return res;
        }
        return null;
    }

    /**
     * 把本矩阵转置
     * @return 转置后的矩阵
     */
    public IntMatrix transpose()
    {
        IntMatrix res = new IntMatrix(getColumn(),getRow(), 0);
        for(int i = 0; i < getRow();i++)
        {
            for(int j = 0;j < getColumn();j++)
            {
                res.intData[j][i] = intData[i][j];
            }
        }
        return res;
    }

    /**
     * 求本矩阵的逆矩阵
     * @return 本矩阵的逆矩阵
     */
    public IntMatrix inverse()
    {
        if(getRow() != getColumn())
        {
            System.out.println("Matrix's row doesn't equal column.");
            return null;
        }
        return new IntMatrix(doubleArrayInt(new Matrix(intArrayDouble(intData)).inverse().getArray()));
    }

    /**
     * 把本矩阵置换密码中的密钥取逆
     * @return 取逆之后的结果矩阵
     */
    public IntMatrix inverseCypto()
    {
        if(getRow() ==2 )
        {
            IntMatrix res = new IntMatrix(getRow(),getColumn(), 0);
            for(int list = 0; list<getColumn();list++)
            {
                if(intData[0][list] != list+1)
                {
                    System.out.println("Cryptography inverse can only uses  in incremental series.");
                    return null;
                }
            }

            for(int j =0;j<getColumn();j++)
            {
                res.intData[0][j] = j+1;
                res.intData[1][intData[1][j]-1] = j+1;
            }
            return res;
        }
        System.out.println("Cryptography inverse can only uses in 2*1 matrix.");
        return null;
    }

    /**
     * 把本矩阵转换为字符二维数组
     * @return 字符二维数组
     */
    public char[][] getChar()
    {
        char[][] res = new char[getRow()][getColumn()];
        for(int i =0;i<getRow();i++)
        {
            for(int j=0;j<getColumn();j++)
            {
                res[i][j] = (char)(intData[i][j]+97);
            }
        }
        return res;
    }

    /**
     * 以字符二维数组的形式初始化本矩阵
     * @param data 输入的二维字符数组
     */
    public void initWithChar(char[][] data)
    {
        for(int i =0;i<getRow();i++)
        {
            for(int j=0;j<getColumn();j++)
            {
                intData[i][j] = Character.getNumericValue(data[i][j]) -10;
            }
        }
    }

    /**
     * 把本矩阵转换为charMatrix
     * @return 转换好的 CharMatrix
     */
    public CharMatrix toCharMatrix()
    {
        CharMatrix charMatrix = new CharMatrix(getRow(),getColumn(), 'a');
        charMatrix.initWithInt(intData);
        return charMatrix;
    }

    /**
     * 获得本矩阵的某一个元素
     * @param row 要获得元素的行号
     * @param column 要获得元素的列号
     * @return 获得的元素
     */
    public int getValue(int row, int column)
    {
        return intData[row][column];
    }

    /**
     * 设置本矩阵的一个元素
     * @param row 要设置元素的行号
     * @param column 要设置元素的列号
     * @param value 要设置的元素的值
     */
    public void setValue(int row, int column, int value)
    {
        intData[row][column] = value;
    }

    /**
     * 获得本矩阵的行数
     * @return 行数
     */
    public int getRow()
    {
        return intData.length;
    }

    /**
     * 获得本矩阵的列数
     * @return 列数
     */
    public int getColumn()
    {
        return intData[0].length;
    }

    /**
     * 打印本矩阵，方便调试
     */
    public void printMatrix()
    {
        for(int[] line: intData)
        {
            for (int ele: line)
            {
                System.out.print(ele+" ");
            }
            System.out.println();
        }
    }

    public boolean checkIsLetter()
    {
        for(int i =0;i<getRow();i++)
        {
            for(int j=0;j<getColumn();j++)
            {

                if(intData[i][j]<0 &&  intData[i][j]>25 )
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 把int类型的二维数组转化为double类型的二维数组
     * @param data 要转换的int型二维数组
     * @return int型二维数组
     */
    public double[][] intArrayDouble(int[][] data)
    {
        if(data == null)
            return null;

        double[][] tempDouble = new double[data.length][data[0].length];
        for(int i=0;i<getRow();i++)
        {
            for(int j=0;j<getColumn();j++)
            {
                tempDouble[i][j] = data[i][j];
            }
        }
        return tempDouble;
    }

    /**
     * 把double类型的二维数组转化为int类型的二维数组
     * @param data 要转换的double型二维数组
     * @return int型二维数组
     */
    public int[][] doubleArrayInt(double[][] data)
    {
        if(data == null)
            return null;
        int[][] resInt = new int[data.length][data[0].length];
        for(int i=0;i<getRow();i++)
        {
            for(int j=0;j<getColumn();j++)
            {
                resInt[i][j] = (int) data[i][j];
            }
        }
        return resInt;
    }
}
