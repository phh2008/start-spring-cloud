package org.example.demo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/2
 */
public class MockitoTest {

    @Test
    public void test1() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");
        //至少被调用了 1 次(atLeastOnce)
        verify(mockedList, atLeastOnce()).add("one");
        //被调用了 1 次(times(1))
        verify(mockedList, times(1)).add("two");
        verify(mockedList, times(3)).add("three times");
        //从未被调用(never)
        verify(mockedList, never()).isEmpty();
    }

    @Test
    public void test2() {
        List list = mock(List.class);
        //when thenReturn来设定 Mock 对象某个方法调用时的返回值
        when(list.add("a")).thenReturn(true);
        when(list.size()).thenReturn(1);

        Assertions.assertTrue(list.add("a"));
        Assertions.assertTrue(list.size() == 1);
        Assertions.assertFalse(list.add("b"));

        Iterator ite = mock(Iterator.class);
        when(ite.next()).thenReturn("hello,").thenReturn("world!");
        String ret = ite.next() + "" + ite.next();
        Assertions.assertEquals("hello,world!", ret);
    }

}
