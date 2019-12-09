public class main {
    public static void main(String[] args) {
          Polynom p = new Polynom("x");
           Polynom p1 = new Polynom("2x");
           Polynom p2 = new Polynom("3x");
        //   complexFunction c = new complexFunction(p,"plus",p1);
        //   complexFunction c1= new complexFunction(c,"times",p2);
     //       System.out.println(c1.toString());
   // String s ="max(max(max(max(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),plus(div(+1.0x+1.0,mul(mul(+1.0x+3.0,+1.0x-2.0),+1.0x-4.0)),2.0)),div(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)),-1.0x^4+2.4x^2+3.1),+0.1x^5-1.2999999999999998x+5.0)";

     //  String s = "min(min(x^2,min(4x+5,-2x)),2x+3)";
        function c = new complexFunction();
       c.initFromString("min(min(x^2,min(4x+5,-2x)),2x+3)");
        System.out.println(c.f(-9));
//       complexFunction c = new complexFunction(p,p1,"plus");
//       complexFunction c1 = new complexFunction(c,p2,"plus");
//       System.out.println(c1.toString());
        //System.out.println(c.toString());


//       complexFunction x = new complexFunction();
//       function y = new complexFunction();
//       y = x.initFromString("div(plus(x,2x),3x)");
        //complexFunction y = null;
        //y = x.initFromString("plus(x,2x)");
//       String s = "div(plus(x,2x),3x)";
//       function y = new complexFunction(s);
//       System.out.println(y.toString());
    }

}
