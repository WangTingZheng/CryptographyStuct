package com.wangtingzheng.cryptographystuct.algorithm;

import com.wangtingzheng.cryptographystuct.matrix.Matrix;

/**
 * @author WangTingZheng
 * @date 2020/4/30 15:33
 * @features 加密算法的模板类，用于继承
 */
public abstract class Algorithm {
    public Matrix messageSpace; //明文空间矩阵
    public Matrix cipherSpace;  //密文空间矩阵
    public Matrix keySpace;  //密钥空间矩阵

    /**
     * 使用三个空间进行初始化，数据结构采用Matrix
     * @param messageSpace 明文空间Matrix
     * @param cipherSpace 密文空间Matrix
     * @param keySpace 密钥空间Matrix
     */
    public Algorithm(Matrix messageSpace, Matrix cipherSpace, Matrix keySpace) {
        this.messageSpace = messageSpace;
        this.cipherSpace = cipherSpace;
        this.keySpace = keySpace;
    }

    /**
     * 使用三个空间进行初始化，数据结构采用String
     * @param message 明文空间字符串
     * @param cipher 密文空间字符串
     * @param key 密钥空间int型二维数组
     */
    public Algorithm(String message, String cipher, int[][] key) {
        messageSpace = new Matrix(message);
        cipherSpace = new Matrix(cipher);
        keySpace = new Matrix(key);
    }

    /**
     * 使用三个空间进行初始化，数据结构采用String，不区分明文空间和密文空间
     * @param space 明文空间或密文空间字符串
     * @param key 密钥空间int型二维数组
     */
    public Algorithm(String space, int[][] key) {
        messageSpace = new Matrix(space);
        cipherSpace = new Matrix(space);
        keySpace = new Matrix(key);
    }

    /**
     * 解密操作
     * 任务是把本类中的cipherSpace经过解密变化
     * 得到一个明文
     * 在把本类中的明文空间messageSpace赋此值
     */
    public abstract void decoding();

    /**
     * 加密操作
     * 任务是把本类中的messageSpace经过加密变化
     * 得到一个密文
     * 在把本类中的密文空间cipherSpace赋此值
     */
    public abstract void encoding();

    /**
     * 检查明文空间messageSpace是否符合加密算法要求
     * @return 如果满足，返回true，否则，返回false
     */
    public abstract boolean checkMessageSpace();

    /**
     * 检查密文空间cipherSpace是否符合加密算法要求
     * @return 如果满足，返回true，否则，返回false
     */
    public abstract boolean checkCipherSpace();

    /**
     * 检查密钥空间keySpace是否符合加密算法要求
     * @return 如果满足，返回true，否则，返回false
     */
    public abstract boolean checkKeySpace();

    /**
     * 总的检查函数，不需要重写
     * @return  如果满足，返回true，否则，返回false
     */
    public final boolean check()
    {
        return checkMessageSpace()&&checkCipherSpace()&&checkKeySpace();
    }

    /**
     * 执行解密操作
     * @return 返回解密完成后的明文空间Matrix
     */
    public final Matrix doDecoding()
    {
        if(check())
        {
            decoding();
            return messageSpace;
        }
        return null;
    }

    /**
     * 执行加密操作
     * @return 返回加密完成后的密文空间Matrix
     */
    public final Matrix doEncoding()
    {
        if(check())
        {
            encoding();
            return cipherSpace;
        }
        return null;
    }
}
