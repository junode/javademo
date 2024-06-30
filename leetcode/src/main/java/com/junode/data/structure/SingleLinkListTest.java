package com.junode.data.structure;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 单向链表。
 * @createTime 2024年05月04日 21:24:00
 */
public class SingleLinkListTest<E> {

    @Test
    public void testBuild() {
        SingleLinkList<Integer> single = new SingleLinkList<>(1, 3);
        System.out.println("single.getFirst() = " + single.getFirst());
        System.out.println(single.getLast());
        single.set(4, 1);
        System.out.println("Arrays.toString(integers) = " + Arrays.toString(single.toArray()));
        single.remove(1);
        System.out.println("Arrays.toString(integers) = " + Arrays.toString(single.toArray()));

    }
}
