package com.wangtingzheng.cryptographystuct.matrix;

import com.wangtingzheng.cryptographystuct.error.Error;
import com.wangtingzheng.cryptographystuct.error.ErrorList;
import com.wangtingzheng.cryptographystuct.error.ErrorTemplate;

import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/5/1 18:06
 * @features
 */
public class Matrix extends ErrorTemplate {

    public static class Operation
    {
        public static int add = 1;
        public static int minus = 2;
        public static int multiply = 3;
    }

    public static class Type
    {
        public static int none = 0;
        public static int IntMatrix = 1;
        public static int CharMatrix = 2;
    }

    public static class ErrorNumber
    {
        public static int MatrixSizeNotMatch = 1;
        public static int ColumnAndRowNotMatchAtTwo = 2;
        public static int ColumnAndRowNotMatch = 3;
        public static int NotIncrementalSeries = 4;
        public static int NotTwoRow = 5;

    }

    private int type;  //矩阵的类型，定义在Matrix.Type中
    public int[][] intData;  //int型矩阵的数据存储二维数组
    public char[][] charData; //char型矩阵的数据存储二维数组
    public int defaultInt = 0;  //默认int型元素值，当二维数组的各行长度不一样时，填补较短行空白元素的值
    public char defaultChar = 'a'; //默认char型元素值，当二维数组的各行长度不一样时，填补较短行空白元素的值


    /**
     * 错误设定初始化函数，设定了在本类中会出现的错误的id和发生时执行的函数
     */
    @Override
    public void errorInit()
    {
        Error MatrixSizeNotMatch = new Error(ErrorNumber.MatrixSizeNotMatch, this.getClass(), errorList) {
            @Override
            public void errorReport() {
                System.out.println("Two matrix have different row number or column number!");
            }
        };
        Error ColumnAndRowNotMatchAtTwo = new Error(ErrorNumber.ColumnAndRowNotMatchAtTwo, this.getClass(),errorList) {
            @Override
            public void errorReport() {
                System.out.println("First matrix column not equals with second matrix row");
            }
        };

        Error ColumnAndRowNotMatch = new Error(ErrorNumber.ColumnAndRowNotMatch, this.getClass(),errorList) {
            @Override
            public void errorReport() {
                System.out.println("Matrix's row doesn't equal column.");
            }
        };

        Error NotIncrementalSeries = new Error(ErrorNumber.NotIncrementalSeries, this.getClass(),errorList) {
            @Override
            public void errorReport() {
                System.out.println("Cryptography inverse can only uses  in incremental series.");
            }
        };

        Error NotTwoRow = new Error(ErrorNumber.NotTwoRow, this.getClass(),errorList) {
            @Override
            public void errorReport() {
                System.out.println("Cryptography inverse can only uses in 2*1 matrix.");
            }
        };


        ErrorList errorList = new ErrorList();
        errorList.addError(MatrixSizeNotMatch);
        errorList.addError(ColumnAndRowNotMatchAtTwo);
        errorList.addError(ColumnAndRowNotMatch);
        errorList.addError(NotIncrementalSeries);
        errorList.addError(NotTwoRow);
        this.errorList = errorList;
    }


    /**
     * 使用int型二维数组创建Matrix对象, 使用默认数字填充空白处，也就是0
     * @param intData 要输入的int型二维数组
     */
    public Matrix(int[][] intData) {
        type = Type.IntMatrix;
        setIntAarry(intData, defaultInt);
    }

    /**
     * 使用int型二维数组创建Matrix对象, 使用传入的数字填充空白处
     * @param intData 要输入的int型二维数组
     * @param defaultInt 空白处填充的数字
     */
    public Matrix(int[][] intData, int defaultInt) {
        this.defaultInt = defaultInt;
        setIntAarry(intData, defaultInt);
    }


    /**
     * 传入int型的二维数组，由于二维数组的每一行有才有端，影响程序运行，所以必须设置一个默认值填充空白处
     * 矩阵的列数将会是最长行的长度，也就是矩阵一定是矩阵，而二维数组不一定，这中间需要有一个转换
     * @param data 要输入的int型二维数组
     * @param defaultInt 空白处填充的数字
     */
    public void setIntAarry(int[][] data, int defaultInt)
    {
        int maxColumn = getArraryMaxCloumn(data);
        int[]temp = new int[maxColumn];
        for (int i= 0; i< data.length;i++)
        {
            if(data[i].length < maxColumn)
            {
                for(int j=0;j<maxColumn;j++)
                {
                    if(j<data[i].length)
                    {
                        temp[j] = data[i][j];
                    }
                    else
                    {
                        temp[j] = defaultInt;
                    }
                    data[i] = temp;
                }
            }
        }
        this.intData = data;
    }

    /**
     * 传入char型的二维数组，由于二维数组的每一行有才有端，影响程序运行，所以必须设置一个默认值填充空白处
     * 矩阵的列数将会是最长行的长度，也就是矩阵一定是矩阵，而二维数组不一定，这中间需要有一个转换
     * @param data 要输入的char型二维数组
     * @param defaultInt 空白处填充的字符
     */
    public void setCharAarry(char[][] data, char defaultInt)
    {
        int maxColumn = getArraryMaxCloumn(data);
        char[] temp = new char[maxColumn];
        for (int i= 0; i< data.length;i++)
        {
            if(data[i].length< maxColumn)
            {
                for(int j=0; j< maxColumn; j++)
                {
                    if(j < data[i].length)
                    {
                        temp[j] = data[i][j];
                    }
                    else
                    {
                        temp[j] = defaultInt;
                    }
                    data[i] = temp;
                }
            }
        }
        this.charData = data;
    }

    /**
     * 创建一个空的(元素值为0)的Matrix对象
     * @param row Matrix的行数
     * @param column Matrix的列数
     * @param defaultInt 元素的默认值
     */
    public Matrix(int row, int column, int defaultInt) {
        type = Type.IntMatrix;
        int[][] data = new int[row][column];
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<column; j++)
            {
                data[i][j] = defaultInt;
            }
        }
        this.intData = data;
    }

    /**
     * 使用Interger列表创建Matrix对象
     * @param integers 要输入的Integer型列表
     */
    public Matrix(List<Integer> integers)
    {
        type = Type.IntMatrix;
        int[][] data = new int[1][integers.size()];
        for(int i = 0; i< integers.size();i++)
        {
            data[0][i] = integers.get(i);
        }
        this.intData = data;
    }


    /**
     * 用字符二维数组新建矩阵对象
     * @param charData 要输入的字符二维数组
     */
    public Matrix(char[][] charData) {
        type = Type.CharMatrix;
        setCharAarry(charData, defaultChar);
    }

    public Matrix(char[][] charData, char defaultChar) {
        type = Type.CharMatrix;
        setCharAarry(charData, defaultChar);
    }


    /**
     * 创建一个空的矩阵，默认字符可设置
     * @param row 矩阵的行数
     * @param column 矩阵的列数
     * @param defaultChar 每个元素的默认字符
     */
    public Matrix(int row, int column, char defaultChar)
    {
        type = Type.CharMatrix;
        char[][] data = new char[row][column];
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<column; j++)
            {
                data[i][j] = defaultChar;
            }
        }
        charData = data;
    }

    /**
     * 使用字符串创建一个一维的矩阵对象
     * @param data 要传入的字符串
     */
    public Matrix(String data) {
        type = Type.CharMatrix;
        char[][] tempCharData = new char[1][data.length()];
        for(int i=0;i<data.length();i++)
        {
            tempCharData[0][i] = data.charAt(i);
        }
        charData = tempCharData;
    }

    /**
     * 检查一个矩阵是否满足运算的一些前提条件
     * @param matrix 要检查的矩阵
     * @param action 要采取的运算，在Matrix中有定义
     * @return 如果满足，返回true，否则返回false
     */
    private boolean check(Matrix matrix, int action)
    {
        int thisRow = this.getRow();
        int thisColumn = this.getColumn();
        int addedRow = matrix.getRow();
        int addedColumn = matrix.getColumn();

        if(action == Operation.add || action == Operation.minus)
        {
            if(thisRow != addedRow || thisColumn != addedColumn)
            {

                errorList.enableError(this.getClass(), ErrorNumber.MatrixSizeNotMatch);
                return false;
            }
        }
        else if(action == Operation.multiply)
        {
            if(thisColumn != addedRow)
            {
                errorList.enableError(this.getClass(),ErrorNumber.ColumnAndRowNotMatchAtTwo);
                return false;
            }
        }
        return true;
    }

    /**
     * 将本矩阵与另外一个矩阵相加，返回一个结果矩阵
     * @param matrix 要与本矩阵相加的矩阵
     * @return 结果矩阵
     */
    public Matrix addWith(Matrix matrix)
    {
        if(check(matrix, Operation.add))
        {
            Matrix matrixRes = new Matrix(getRow(), getColumn(), 0);

            for(int i=0;i<getRow();i++)
            {
                for(int j =0;j<getColumn(); j++)
                {
                    matrixRes.setValue(i, j, getIntValue(i, j)+ matrix.getIntValue(i, j));
                }
            }
            return matrixRes;
        }
        return null;
    }

    /**
     * 把本矩阵减去一个矩阵
     * @param matrix 本矩阵减去的矩阵
     * @return 减的结果矩阵
     */
    public Matrix minusWith(Matrix matrix)
    {
        if(check(matrix, Operation.minus))
        {
            Matrix matrixRes = new Matrix(getRow(), getColumn(), 0);

            for(int i=0;i<getRow();i++)
            {
                for(int j =0;j<getColumn();j++)
                {
                    matrixRes.setValue(i,j,getIntValue(i,j)-matrix.getIntValue(i,j));
                }
            }
            return matrixRes;
        }
        return null;
    }

    /**
     * 返回本矩阵与另外一个矩阵相乘的结果
     * @param matrix 要与本矩阵相乘的矩阵
     * @return 乘法的结果
     */
    public Matrix multiplyWith(Matrix matrix)
    {
        if(check(matrix, Operation.multiply))
        {
            Matrix res  = new Matrix(getRow(), matrix.getColumn(), 0);
            Matrix tras = matrix.transpose();
            int a = 0;
            int b = 0;
            for(int i =0; i < getRow();i++)
            {
                for(int j =0; j < matrix.getColumn();j++)
                {
                    int result = 0;
                    for(int z = 0; z < getColumn();z++)
                    {
                        result += getIntValue(a,z) * tras.getIntValue(b,z);
                    }
                    res.setValue(i,j,result);
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
    public Matrix transpose()
    {
        Matrix res = new Matrix(getColumn(),getRow(), 0);
        for(int i = 0; i < getRow();i++)
        {
            for(int j = 0;j < getColumn();j++)
            {
                res.setValue(j,i, getIntValue(i,j)) ;
            }
        }
        return res;
    }

    /**
     * 求本矩阵的逆矩阵
     * @return 本矩阵的逆矩阵
     */
    public Matrix inverse()
    {
        if(getRow() != getColumn())
        {
            errorList.enableError(this.getClass(), ErrorNumber.ColumnAndRowNotMatch);
            return null;
        }
        return new Matrix(doubleArrayInt(new Jama.Matrix(intArrayDouble(intData)).inverse().getArray()));
    }

    /**
     * 把本矩阵置换密码中的密钥取逆
     * @return 取逆之后的结果矩阵
     */
    public Matrix inverseCypto()
    {
        if(getRow() == 2)
        {
            Matrix res = new Matrix(getRow(),getColumn(), 0);
            for(int list = 0; list<getColumn();list++)
            {
                if(getIntValue(0, list) != list + 1)
                {
                    errorList.enableError(ErrorNumber.NotIncrementalSeries);
                    return null;
                }
            }

            for(int j =0;j<getColumn();j++)
            {
                res.setValue(0,j, j+1);
                res.setValue(1, getIntValue(1,j)-1,j+1);
            }
            return res;
        }
        errorList.enableError(ErrorNumber.NotTwoRow);
        return null;
    }


    /**
     * 获得本矩阵的行数
     * @return 行数
     */
    public int getRow()
    {
        if(type == Type.CharMatrix)
        {
            return charData.length;
        }
        return intData.length;
    }

    /**
     * 获得本矩阵的列数
     * @return 列数
     */
    public int getColumn()
    {
        if(type == Type.CharMatrix)
        {
            if(charData == null)
                return 0;
            return charData[0].length;
        }
        if(intData == null)
            return 0;
        return intData[0].length;
    }

    /**
     * 获取char类型的元素，如果矩阵不是char型的，元素将被转化为char型
     * @param row 要获取元素的行号
     * @param column 要获取元素的列号
     * @return int型的元素值
     */
    public char getCharValue(int row, int column)
    {
        if(type == Type.IntMatrix)
            return intToChar(intData[row][column]);
        return charData[row][column];
    }

    /**
     * 获取int类型的元素，如果矩阵不是int型的，元素将被转化为int型
     * @param row 要获取元素的行号
     * @param column 要获取元素的列号
     * @return int型的元素值
     */
    public int getIntValue(int row, int column)
    {
        if(type == Type.CharMatrix)
            return charToInt(charData[row][column]);
        return intData[row][column];
    }

    /**
     * 设置矩阵中的每一个值，注意
     * 如果你修改了char类型的矩阵的一个值为int型数据
     * 修改输入的值会被转化为矩阵的元素类型
     * @param row 要修改元素的行号
     * @param column 要修改元素的列号
     * @param value 要修改的值，是int型的
     */
    public void setValue(int row, int column, int value)
    {
        if(type == Type.CharMatrix)
        {
            charData[row][column] = intToChar(value);
        }
        else
        {
            intData[row][column] = value;
        }
    }


    /**
     * 设置矩阵中的每一个值，注意
     * 如果你修改了int类型的矩阵的一个值为char型数据
     * 修改输入的值会被转化为矩阵的元素类型
     * @param row 要修改元素的行号
     * @param column 要修改元素的列号
     * @param value 要修改的值，是char型的
     */
    public void setValue(int row, int column, char value)
    {
        if(type == Type.IntMatrix)
        {
            intData[row][column] = charToInt(value);
        }
        else
        {
            charData[row][column] = value;
        }
    }

    /**
     * 检查二维数组的元素是否位于0到25
     * @return 如果全是，返回true，否则返回false
     */
    public boolean checkIsLetter()
    {
        for(int i =0;i<getRow();i++)
        {
            for(int j=0;j<getColumn();j++)
            {
                int value;
                if(type == Type.IntMatrix)
                    value = getIntValue(i,j);
                else
                    value = charToInt(charData[i][j]);

                if(value<0 || value>25)
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


    /**
     * 把int型字符转化为char型字符
     * @param data 要转化的int型字符
     * @return 转化好的char型字符
     */
    public char intToChar(int data)
    {
        return (char)(data + 97);
    }

    /**
     * 把char型字符转化为int型字符
     * @param data 要转化的char型字符
     * @return 转化好的int型字符
     */
    public int charToInt(char data)
    {
        return Character.getNumericValue(data) -10;
    }


    /**
     * 把int型的二维数组转化为char型的二维数组，规则是a-0,z-25
     * @param data 要转化的int型二维数组
     * @return 转化好的char型二维数组
     */
    public char[][] intToChar(int[][] data)
    {
        char[][] res = new char[getRow()][getColumn()];
        for(int i =0;i<getRow();i++)
        {
            for(int j=0;j<getColumn();j++)
            {
                res[i][j] = intToChar(data[i][j]);
            }
        }
        return res;
    }

    /**
     * 把char型的二维数组转化为int型的二维数组，规则是a-0,z-25
     * @param data 要转化的char型二维数组
     * @return 转化好的int型二维数组
     */
    public int[][] charToInt(char[][] data)
    {
        int[][] res = new int[getRow()][getColumn()];
        for(int i =0;i<getRow();i++)
        {
            for(int j=0;j<getColumn();j++)
            {
                res[i][j] = charToInt(data[i][j]);
            }
        }
        return res;
    }


    /**
     * 打印本矩阵，方便调试
     */
    public void printMatrix()
    {
        for(int i=0; i<getRow();i++)
        {
            for(int j =0;j<getColumn();j++)
            {
                if(type == Type.IntMatrix)
                    System.out.print(getIntValue(i,j)+" ");
                else
                    System.out.print(getCharValue(i,j)+" ");
            }
            System.out.println();
        }
    }

    /**
     * 把本矩阵转换为charMatrix, 并返回
     * @return 转换好的 CharMatrix
     */
    public Matrix toCharMatrix()
    {
        return new Matrix(intToChar(intData));
    }



    /**
     * 把本矩阵转化为int型的矩阵
     * @return 转换好的 IntMatrix
     */
    public Matrix toIntMatrix()
    {
        return new Matrix(charToInt(charData));
    }


    /**
     * 把本矩阵转换为charMatrix
     */
    public void changeToCharMatrix()
    {
        if(type != Type.CharMatrix)
        {
            charData = intToChar(intData);
            type = Type.CharMatrix;
        }
    }

    /**
     * 把本矩阵转化为int型的矩阵
     */
    public void changeToIntMatrix()
    {
        if(type != Type.IntMatrix)
        {
            intData = charToInt(charData);
            type = Type.IntMatrix;
        }
    }


    /**
     * 返回矩阵的类型，是int型的还是char型的
     * @return 如果是int的，将会返回Type.IntMatrix的值，如果是char型的，就会返回Type.CharMatrix的值
     */
    public int getType() {
        return type;
    }

    /**
     * 获得一个二维int数组的最大列数
     * @param data 要操作的int型二维数组
     * @return 最大列数
     */
    public int getArraryMaxCloumn(int[][] data)
    {
        if(data == null)
            return 0;
        int max = 0;
        for(int[] list:data)
        {
            if(list.length > max)
                max = list.length;
        }
        return max;
    }


    /**
     * 获得一个二维char数组的最大列数
     * @param data 要操作的char型二维数组
     * @return 最大列数
     */
    public int getArraryMaxCloumn(char[][] data)
    {
        if(data == null)
            return 0;
        int max = 0;
        for(char[] list:data)
        {
            if(list.length > max)
                max = list.length;
        }
        return max;
    }
}