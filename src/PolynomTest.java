public class PolynomTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}
	public static void test1() {
		System.out.println("*****  test1  *****");
		Polynom p = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2", "-3x", "-46", "0", "2x^5"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p.add(m);
		}
		System.out.println("p: " + p);
		System.out.println("f(2) = " + p.f(2));
		Polynom p2 = new Polynom("2x^5");
		System.out.println("p2: " +p2);
		p.substract(p2);
		System.out.println("p-p2: " + p);
		p.multiply(p2);
		System.out.println("p*p2: " + p);
		Polynom p3 = new Polynom ("2x^5");
		System.out.println("p3: " + p3);
		System.out.println("equals: " + p2.equals(p3));
		System.out.println("iszero: " + p.isZero());
		p.substract(p);
		System.out.println("p-p: " + p);
		System.out.println("iszero:" + p.isZero());
	}
	public static void test2() {
		System.out.println("*****  test2  *****");
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("equals: " + p1.equals(p2));
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
	}
	public static void test3() {
		System.out.println("*****  test3  *****");
		Polynom p = new Polynom("2x+x^2");
		System.out.println("root: " + p.root(-3, -1, 0.000001));
		System.out.println("area: " + p.area(6, 4, 0.0000001));
		Polynom p1 = new Polynom("5+2x-4x^2-3x^4");
		System.out.println("derivative: " + p1.derivative());
		String s = p1.toString();
		Polynom p2 = new Polynom(s);
		System.out.println("equals: " + p1.equals(p2));
	}
}
