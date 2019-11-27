import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonomTest {

    @org.junit.jupiter.api.Test
    void derivative() {
        Monom m1 = new Monom ("4");
        Monom m2 = new Monom ("6.4x");
        Monom m3 = new Monom ("5x^2");
        assertEquals(new Monom("0") , m1.derivative());
        assertEquals(new Monom("6.4") , m2.derivative());
        assertEquals(new Monom("10x") , m3.derivative());
    }

    @org.junit.jupiter.api.Test
    void f() {
        Monom m1 = new Monom ("10x");
        Monom m2 = new Monom ("4x^2");
        Monom m3 = new Monom ("2x^4");
        assertEquals(30 , m1.f(3));
        assertEquals(100 , m2.f(5));
        assertEquals(32 , m3.f(2));
    }

    @org.junit.jupiter.api.Test
    void isZero() {
        Monom m1 = new Monom ("0");
        Monom m2 = new Monom ("5x");
        assertEquals(true , m1.isZero());
        assertEquals(false , m2.isZero());
    }

    @org.junit.jupiter.api.Test
    void add() {
        Monom m1 = new Monom ("5x");
        Monom m2 = new Monom ("10x");
        Monom m3 = new Monom ("40x^3");
        Monom m4 = new Monom ("2.5x^3");
      //  assertEquals((new Monom("15x") , m1.add(m2));
    }

    @org.junit.jupiter.api.Test
    void multipy() {
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }

    @org.junit.jupiter.api.Test
    void initFromString() {
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
    }

    @org.junit.jupiter.api.Test
    void copy() {
    }
}