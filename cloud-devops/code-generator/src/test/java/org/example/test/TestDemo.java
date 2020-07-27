package org.example.test;


import org.junit.jupiter.api.Test;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/29
 */
public class TestDemo {

    @Test
    public void test1() {

        System.out.println(System.getProperty("user.dir"));
        System.out.println(this.getClass().getResource("/").getPath());
        System.out.println(this.getClass().getResource("").getPath());
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());

    }

}
