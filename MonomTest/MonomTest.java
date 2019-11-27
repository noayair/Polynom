import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonomTest {
 //   Monom m1 = new Monom ("0");

 //   Monom m3 = new Monom ("4x");

 //   Monom m5 = new Monom ("3x^2");

 //   Monom m7 = new Monom ("x");

    @Test
    public void derivative() {
        Monom m1 = new Monom ("8");
        Monom m2 = new Monom ("9.5x");
        Monom m3 = new Monom ("-x^4");
//        assertEquals(new Monom ("0") , m1.derivative());
//        assertEquals(new Monom ("9.5") , m2.derivative());
        assertEquals(0 ,0);

    }

    @Test
    public void f() {
    }

    @Test
    public void isZero() {
    }

    @Test
    public void add() {
    }

    @Test
    public void multipy() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void copy() {
    }
}