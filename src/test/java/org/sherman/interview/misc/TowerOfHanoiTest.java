package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 26/12/2016
 */
public class TowerOfHanoiTest {
    @Test
    public void move() {
        int disks = 4;
        TowerOfHanoi.NamedStack src = new TowerOfHanoi.NamedStack("A", disks);
        src.push(new TowerOfHanoi.Disk(4));
        src.push(new TowerOfHanoi.Disk(3));
        src.push(new TowerOfHanoi.Disk(2));
        src.push(new TowerOfHanoi.Disk(1));
        TowerOfHanoi.NamedStack aux = new TowerOfHanoi.NamedStack("B", disks);
        TowerOfHanoi.NamedStack dst = new TowerOfHanoi.NamedStack("C", disks);

        TowerOfHanoi.towerOfHanoi(disks, src, aux, dst);

        assertEquals(dst.pop().size, 1);
        assertEquals(dst.pop().size, 2);
        assertEquals(dst.pop().size, 3);
        assertEquals(dst.pop().size, 4);
    }
}
