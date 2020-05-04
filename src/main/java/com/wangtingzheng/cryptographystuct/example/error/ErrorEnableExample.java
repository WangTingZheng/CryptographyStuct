package com.wangtingzheng.cryptographystuct.example.error;

import com.wangtingzheng.cryptographystuct.error.Error;
import com.wangtingzheng.cryptographystuct.error.ErrorList;
import com.wangtingzheng.cryptographystuct.matrix.Matrix;

/**
 * @author WangTingZheng
 * @date 2020/5/4 11:16
 * @features
 */
public class ErrorEnableExample {
    public static void main(String[] args)
    {
        ErrorList errorList = new ErrorList();
        Error error = new Error(1, Matrix.class,errorList) {
            @Override
            public void errorReport() {
                System.out.println("hello world");
            }
        };
        errorList.addError(error);
        error.enable();
        error.disable();
        System.out.println("disable:"+errorList.getDisableError().contains(error));
        System.out.println("able:"+ errorList.getEnableError().contains(error));
    }
}
