package org.CBank.Test;

/**
 * Created by man11 on 2017/9/28.
 */
public class Test {
    public static void main(String[] args){
        Test test = new Test();
        test.getA();
    }
    public void getA(){
        String a = "123";
        change(a);
        System.out.println(a);
    }

    private void change(String a) {
        a = "234";
    }
}
