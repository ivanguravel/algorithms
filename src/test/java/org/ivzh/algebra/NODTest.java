package org.ivzh.algebra;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NODTest {

    NOD nod = new NOD();

    @Test
    public void simpleNodTest() {
        System.out.println("simple nod algorithm test and time");
        long start = System.nanoTime();
        assertTrue(nod.nod(1234567890, 2) == 2);
        long end = System.nanoTime() - start;
        double seconds = (double)end / 1_000_000_000.0;
        System.out.println("operation takes: " + seconds + " seconds");
    }

    @Test
    public void bitsNodTest() {
        System.out.println("bits nod algorithm test and time");
        long start = System.nanoTime();
        assertTrue(nod.bitsNod(1234567890, 2) == 2);
        long end = System.nanoTime() - start;
        double seconds = (double)end / 1_000_000_000.0;
        System.out.println("operation takes: " + seconds + " seconds");
    }

    @Test
    public void recursiveNodTest() {
        System.out.println("recursive nod algorithm test and time");
        long start = System.nanoTime();
        assertTrue(nod.bitsNod(1234567890, 2) == 2);
        long end = System.nanoTime() - start;
        double seconds = (double)end / 1_000_000_000.0;
        System.out.println("operation takes: " + seconds + " seconds");
    }
}
