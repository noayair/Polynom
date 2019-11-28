import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;


//import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	LinkedList<Monom> theList;

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		LinkedList<Monom> temp = new LinkedList<Monom>();
		theList = temp;
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		int j = 0;
		LinkedList<Monom> list = new LinkedList<Monom>();
		for (int i = 0; i < s.length();) { // create a new linkedlist of monoms
			if ((s.charAt(i) == '-' || s.charAt(i) == '+') && i != 0) {
				Monom m = new Monom(s.substring(j, i));
				j = i;
				list.add(m);
				i++;
			}else {
				i++;
			}
		}
		if (s.charAt(j) == '+') {
			j++;
		}
		Monom m = new Monom (s.substring(j));
		list.add(m);
		theList = list;
		this.organize();
		this.sort();
	}

	public void sort() { //sort the polynom
		Comparator<Monom> sort = new Monom_Comperator();
		this.theList.sort(sort);

	}

	public void organize() {
		for (int i = 0; i < theList.size(); i++) {
			for (int j = i+1; j < theList.size();) {
				if (theList.get(i).get_power() == theList.get(j).get_power()) {
					theList.get(i).add(theList.get(j));
					theList.remove(j);
					j++;
				}else {
					j++;
				}
			} 
			if (theList.get(i).get_coefficient() == 0 && !this.isZero()) {
				theList.remove(i);
			}
		}
	}


	@Override
	public double f(double x) {
		double j = 0;
		for (int i = 0; i < theList.size(); i++) {
			Monom m = this.theList.get(i);
			double t = m.f(x);
			j += t;
		}
		return j;
	}


	public void add(Monom m1) { //add monom to polynom
		this.theList.add(m1);
		this.organize();
		this.sort();
	}



	@Override
	public void add(Polynom_able p1) { //add polynom to polynom
		Iterator<Monom> runner = p1.iteretor();
		while(runner.hasNext()) {
			Monom temp = runner.next();
			this.add(temp);
		}
		this.organize();
		this.sort();
	}

	public void substract(Polynom_able p1) {
		Monom minus = new Monom ("-1");
		LinkedList<Monom> list = new LinkedList<Monom>();
		Monom m = new Monom (0,0);
		list.add(m);
		if (this.equals(p1)) {
			this.theList = list;
		}
		Polynom_able p = p1.copy();
		p.multiply(minus);
		this.add(p);
		this.organize();
		this.sort();
	}

	public void multiply(Monom m1) { //polynom*monom
		Iterator<Monom> runner = this.theList.iterator();	
		while(runner.hasNext()) {
			Monom temp = runner.next();
			temp.multipy(m1);
		}
		this.organize();
		this.sort();
	}

	@Override
	public void multiply(Polynom_able p1) { //polynom*polynom
		LinkedList<Monom> list = new LinkedList<Monom>(); //new list
		Iterator<Monom> runnerp1 = p1.iteretor(); //iterator for p1
		while(runnerp1.hasNext()) { //while theList is not over
			Monom temp1 = runnerp1.next(); //save the monom in new temp
			Polynom_able copy = this.copy();
			Iterator<Monom> runnerCopy = copy.iteretor(); //iterator for theList
			while (runnerCopy.hasNext()) { //same with p1
				Monom temp = runnerCopy.next();
				temp.multipy(temp1); //
				list.add(temp);
			} 
		}
		this.theList = list;
		this.organize();
		this.sort();
	}

	//@Override
	public boolean equals(Object o) {
		if (!(o instanceof Polynom_able)) return  false;
		Polynom p1 = (Polynom) o;
		Iterator<Monom> runner = this.theList.iterator();
		Iterator<Monom> runnerp1 = p1.iteretor();
		while(runner.hasNext() && runnerp1.hasNext()) {
			Monom temp = runner.next();
			Monom temp1 = runnerp1.next();
			if (!temp.equals(temp1)) return false;
		}
		return true;
	}

	@Override
	public boolean isZero() {
		Iterator<Monom> runner = this.theList.iterator();
		while(runner.hasNext()) {
			Monom temp = runner.next();
			if (!temp.isZero()) return false;
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if(this.f(x0) * this.f(x1) > 0) throw new RuntimeException();
		if ( x0 > x1) {
			double temp1 = x0;
			x0 = x1;
			x1 = temp1;
		}
		double mid = 0;
		while (x1 - x0 >= eps) {
			mid = (x0 + x1)/2;
			if (this.f(mid) * this.f(x0) == 0) {
				return mid;
			}
			else if (this.f(mid) * this.f(x0) > 0) {
				x0 = mid;
			}else {
				x1 = mid;
			}
		}
		return mid;
	}

	@Override
	public Polynom_able copy() { //copy
		Polynom new1 = new Polynom();
		for (int i = 0; i < theList.size(); i++) {
			new1.add(new Monom (this.theList.get(i)));
		}
		return new1;

	}

	@Override
	public Polynom_able derivative() {
		Polynom new1 = new Polynom();
		Iterator<Monom> runner = this.theList.iterator();
		while(runner.hasNext()) {
			Monom temp = runner.next();
			if (temp.get_power() == 0) {
				Monom newMonom = new Monom (0,0);
				new1.add(newMonom);
			}else {
				Monom newMonom = new Monom((temp.get_coefficient() * temp.get_power()),temp.get_power()-1);
				new1.add(newMonom);
			}
		}
		new1.organize();
		new1.sort();
		return new1;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		if ( x0 > x1) {
			double temp1 = x0;
			x0 = x1;
			x1 = temp1;
		}
		double ans = 0;
		double temp2 = x0 + eps;
		while (temp2 < x1) {
			ans += this.f(temp2) *eps;
			temp2 += eps;
		}
		return ans;
	}

	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> runner = this.theList.iterator();
		return runner;
	}
	@Override


	public String toString() {
		Iterator<Monom> runner = this.theList.iterator();
		String ans = "";
		while(runner.hasNext()) {
			Monom temp = runner.next();
			if (temp.get_coefficient() > 0) {
				ans += "+";
				ans += temp.toString();
			}else {
				ans += temp.toString();
			}
		}
		System.out.println(ans);
		return ans;
	}

	@Override
	public function initFromString(String s) {
		return null;
	}

//	public void substract(Polynom_able p1) {				//substract polynom from polynom
//		Iterator<Monom> it = p1.iteretor();					//create a pointer to the monoms
//		Monom minus = new Monom ("-1");
//		while(it.hasNext()) {
//			Monom m = it.next();
//			for(int i = 0; i < theList.size(); i++) {
//				if(theList.get(i).get_power() == m.get_power()) {	//if the power is equal
//					m.multipy(minus);
//					theList.get(i).add(m);	//substruct the coefficients of the monoms
//					m.multipy(minus);
//					break;
//				}
//				else if(i == theList.size() - 1) {			//if it doesnt have an equal power at the polynom
//					m.multipy(minus);
//					theList.add(m);
//					m.multipy(minus);		//set it as a minus number
//					break;
//				}
//			}
//		}
//		this.organize();
//		this.sort();		//sort the list
//	}
}
