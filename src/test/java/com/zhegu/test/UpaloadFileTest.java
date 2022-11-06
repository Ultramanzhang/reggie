package com.zhegu.test;

import org.junit.jupiter.api.Test;

public class UpaloadFileTest {

    @Test
    public void test1(){
        String fileName = "ererewqer.jpg";

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }
}
