import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}

	public Monom(String s) {
		try {
			int t = s.indexOf('x');
			if (t == -1) {
				set_coefficient(Double.parseDouble(s));
				set_power(0);
			}else if (t == 0){
				set_coefficient(1);
				if (s.charAt(s.length()-1) == 'x') {
					set_power(1);
				}else {
					set_power(Integer.parseInt(s.substring(t+2)));
				}
			}else {
				if (s.charAt(0) == '-' && s.charAt(1) == 'x') {
					set_coefficient(-1);
				}else {
					set_coefficient(Double.parseDouble(s.substring(0, t)));
				}
				if (t == s.length()-1) {
					set_power(1);
				}else {
					set_power(Integer.parseInt(s.substring(t+2)));
				}
			}
		}catch(Exception E) {
			System.out.println("Error - the monom is not correct");
		}
	}


	public void add(Monom m) {
		if (this.get_power() == m._power)
			set_coefficient (this.get_coefficient() + m._coefficient); 
	}

	public void multipy(Monom d) {
		set_coefficient (this.get_coefficient() * d._coefficient);
		set_power (this.get_power() + d._power);
	}

	public String toString() {
		if (this.get_coefficient() == 0 && this.get_power() == 0) return "0";
		String ans = this.get_coefficient() + "x^" + this.get_power();
		return ans;
	}

	@Override
	public function initFromString(String s) {
		return null;
	}

	public boolean equals(Monom m) {
		if (m.get_power() != this.get_power()) return false;
		if (m.get_coefficient() == 0 && this.get_coefficient() == 0) return true;
		if ((Math.abs(this.get_coefficient() - m.get_coefficient())) > Monom.EPSILON) return false;
		return true;
	}

	public function copy() {
		Monom m = new Monom(0,0);
		m._coefficient = this._coefficient;
		m._power = this._power;

		return null;
	}

	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;

}
