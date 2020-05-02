package com.wangtingzheng.cryptographystuct.matrix;

/**
 * @author WangTingZheng
 * @date 2020/5/1 16:05
 * @features
 */
public class CharMatrix extends MatrixClass {
    public char charData[][] = null;

    /**
     * 用字符二维数组新建矩阵对象
     * @param charData 要输入的字符二维数组
     */
    public CharMatrix(char[][] charData) {
        type = Type.CharMatrix;
        this.charData = charData;
    }

    /**
     * 创建一个空的矩阵，默认字符可设置
     * @param row 矩阵的行数
     * @param column 矩阵的列数
     * @param defalutChar 每个元素的默认字符
     */
    public CharMatrix(int row, int column, char defalutChar)
    {
        type = Type.CharMatrix;
        char[][] data = new char[row][column];
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                data[i][j] = defalutChar;
            }
        }
        charData = data;
    }

    /**
     * 使用字符串创建一个一维的矩阵对象
     * @param data 要传入的字符串
     */
    public CharMatrix(String data) {
        type = Type.CharMatrix;
        char[][] tempCharData = new char[1][data.length()];
        for(int i=0;i<data.length();i++)
        {
            tempCharData[0][i] = data.charAt(i);
        }
        charData = tempCharData;
    }


    /**
     * 把本矩阵转化为int型的矩阵
     * @return
     */
    public IntMatrix toIntMatrix()
    {
        IntMatrix intMatrix = new IntMatrix(getRow(), getColumn(), 0);
        intMatrix.initWithChar(charData);
        return intMatrix;
    }

    /**
     * 通过重新传入整型二维数组初始化矩阵
     * @param data 要传入的整型二维数组
     */
    public void initWithInt(int[][] data)
    {
        for(int i =0;i<getRow();i++)
        {
            for(int j=0;j<getColumn();j++)
            {
                charData[i][j] = (char)(data[i][j]+97);
            }
        }
    }

    /**
     * 检查矩阵中的各个元素是否都是阿拉伯字母
     * @return 如果都是，则返回true，否则返回false
     */
    public boolean checkIsLetter()
    {
        IntMatrix intMatrix = toIntMatrix();
        return intMatrix.checkIsLetter();
    }

    /**
     * 获得本矩阵的行数
     * @return 行数
     */
    public int getRow()
    {
        return charData.length;
    }

    /**
     * 获得本矩阵列数
     * @return 列数
     */
    public int getColumn()
    {
        return charData[0].length;
    }

    /**
     * 打印本矩阵，方便调试
     */
    public void printMatrix()
    {
        for(char[] line: charData)
        {
            for (char ele: line)
            {
                System.out.print(ele+" ");
            }
            System.out.println("");
        }
    }

    /**
     * 获得本矩阵的某行某列的字符值
     * @param row 行号
     * @param column 列号
     * @return 字符值
     */
    public char getChar(int row, int column)
    {
        return charData[row][column];
    }

    /**
     * 设置本矩阵的某行某列的字符值
     * @param row 行号
     * @param column 列好
     * @param value 字符值
     */
    public void setChar(int row, int column, char value)
    {
        charData[row][column] = value;
    }
}
