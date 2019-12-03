public class complexFunction {
    private Node head;

    complexFunction(Node left , Node right ,function f,  Operation op){

    }

    public double f(double x){
        return 0;
    }

    public String toString(){
        return null;
    }

    public String chekEnum(){
        switch(this.op){
            case Plus:
                return "plus";
            case Divid:
                return "divid";
            case Max:
                return "max";
            case Min:
                return "min";
            case Comp:
                return "comp";
            case Times:
                return "times";
            case None:
                return "none";
            case Error:
                return "error";
        }
        return null;
    }

    public function initFromString(String s){
        int t = s.indexOf('(');
        String s1 = s.substring(0 , t);
        if(chekEnum() == s1) {
            this.op = Operation.valueOf(s1);
        }
        return null;
    }
    public function copy() {
        return null;
    }
    public boolean equals(Object obj){
        return false;
    }

    public void plus(function f1){
        complexFunction c = new complexFunction(this.left , this.right , this.op);
        //   complexFunction c1 = new complexFunction(c , f1 , Operation.Plus);
    }
    public void mul(function f1){
    }

    public void div(function f1){
    }

    public void max(function f1){
    }

    public void min(function f1){
    }

    public void comp(function f1){
    }

    public function left(){
        return null;
    }

    public function right(){
        return null;
    }

    public Operation getOp(){
        return null;
    }
}