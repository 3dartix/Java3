package DZ;

import Lesson_6.DZ.DZ;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Tasks {
    DZ dz = new DZ();

    @Before
    public void init() {
        System.out.println("init");
        dz = new DZ();
    }
    @Test
    public void testTask2_1() {
        Assert.assertArrayEquals(new int[] {1,7}, dz.task2(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}));
    }
    @Test (expected = RuntimeException.class)
    public void testTask2_2() {
        Assert.assertNotNull(dz.task2(new int[]{1}));
    }
    @Test
    public void testTask2_3() {
        Assert.assertArrayEquals(new int[] {}, dz.task2(new int[]{1, 2, 4}));
    }

    @Test
    public void testTask3_1() {
        Assert.assertTrue(dz.task3(new int[]{1,4}));
    }
    @Test
    public void testTask3_2() {
        Assert.assertFalse(dz.task3(new int[]{1}));
    }
    @Test
    public void testTask3_3() {
        Assert.assertFalse(dz.task3(new int[]{1,2,3,7,3}));
    }
}

