package com.wangtingzheng.cryptographystuct.example.error;

import com.wangtingzheng.cryptographystuct.error.Error;
import com.wangtingzheng.cryptographystuct.error.ErrorTemplate;

/**
 * @author WangTingZheng
 * @date 2020/5/4 11:17
 * @features
 */
public class ErrorSetExample extends ErrorTemplate {
    public Error error;

    public void useError()
    {
        error.enable();
    }

    public void useList()
    {
        errorList.enableError(this.getClass(), ErrorNumber.TestError);
    }
    @Override
    public void errorInit() {
         error = new Error(ErrorNumber.TestError, this.getClass(), errorList) {
            @Override
            public void errorReport() {
                System.out.println("This is a test error, error has been enable");
            }
        };
    }

    public static class ErrorNumber
    {
        public static int  TestError =1;
    }
}
