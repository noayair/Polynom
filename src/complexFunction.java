

public class complexFunction implements function {
    private Operation op;
    private function left;
    private function right;

    public complexFunction(function f1, Operation op, function f2) {
        this.left=f1;
        this.op=op;
        this.right=f2;
    }
    public complexFunction(){
        this.left=null;
        this.right=null;
        this.op=null;
    }
    public void set_OP(Operation op) {
        this.op=op;
    }
    public void set_right(function right) {
        this.right=right;
    }
    public void set_left(function left) {
        this.left=left;
    }
    public complexFunction(function f1, String op, function f2) {
        this.left = f1;
        this.right = f2;
        switch (op) {
            case "plus":
                this.op = Operation.Plus;
                break;
            case "div":
                this.op = Operation.Divid;
                break;
            case "max":
                this.op = Operation.Max;
                break;
            case "min":
                this.op = Operation.Min;
                break;
            case "comp":
                this.op = Operation.Comp;
                break;
            case "times":
                this.op = Operation.Times;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        }
    }
    public double f(double x) {
        if (this.right != null) {
            switch (op) {
                case Plus:
                    return ((this.left.f(x)) + (this.right.f(x)));
                case Divid:
                    return ((this.left.f(x)) / (this.right.f(x)));
                case Max:
                    return Math.max((this.left.f(x)), (this.right.f(x)));
                case Min:
                    return Math.min((this.left.f(x)), (this.right.f(x)));
                case Comp:
                    return this.left.f(this.right.f(x));
                case Times:
                    return ((this.left.f(x)) * (this.right.f(x)));
                case None:
                    return this.left.f(x);
                case Error:
                    return 0;
            }

        }
        return this.left.f(x);
        }

//        public boolean isPolynom (String s){
//        int count1 = 0;
//        int count2 = 0;
//        int count3 = 0;
//        for(int i=0; i<s.length(); i++){
//            if(s.charAt(i)=='('){
//                count1++;
//            }
//           else if(s.charAt(i)==')'){
//                count2++;
//            }
//            else if(s.charAt(i)==','){
//                count3++;
//            }
//            if( count1==1 && count2==1 && count3==1){
//                return true;
//            }
//        }
//        return false;
//        }

//    public complexFunction leftCf(String s){
//        complexFunction left=new complexFunction();
//        s=s.substring(0,s.length()-1);
//        int count1=0;
//        int count2=0;
//        int soger= s.indexOf(')');
//        for(int i=0; i<s.length(); i++){
//            if(s.charAt(i)=='('){
//                count1++;
//            }
//            if(s.charAt(i)=='('){
//                count2++;
//            }
//            if(count1-count2==0 && soger+1==','){
//                s=s.substring(0,soger+1);
//                left.initFromString(s);
//
//            }
//        }
//        return left;
//    }
//    public complexFunction rightCf(String s){
//        complexFunction right=new complexFunction();
//        s=s.substring(0,s.length()-1);
//        int count1=0;
//        int count2=0;
//        int soger= s.indexOf(')');
//        for(int i=0; i<s.length(); i++){
//            if(s.charAt(i)=='('){
//                count1++;
//            }
//            if(s.charAt(i)=='('){
//                count2++;
//            }
//            if(count1-count2==0 && soger+1==','){
//                s=s.substring(soger+2);
//                right.initFromString(s);
//            }
//        }
//        return right;
//    }

    public function initFromString(String s) {
        int start = s.indexOf("(");
        int end = s.length()-1;
        int psik = findPsik(s);
        this.op = Op(s.substring(0, start));
        // left side
        if (isCf(s.substring(start + 1,psik))){
            this.left = new complexFunction();
            this.left.initFromString(s.substring(start + 1, psik));
        } else {
            this.left = new Polynom(s.substring(start + 1, psik));
        }
        //right side
        if (isCf(s.substring(psik + 1, end))){
            this.right = new complexFunction();
            this.right.initFromString(s.substring(psik + 1, end));
        } else {
            this.right = new Polynom(s.substring(psik + 1, end));
        }
        return this;
    }


    public boolean isCf(String s){
       if( s.charAt(0) == 'p' || s.charAt(0) == 'd' || s.charAt(0)==  'm' || s.charAt(0) == 'c' || s.charAt(0) == 'n'){
           return true;
       }
       return false;
    }
    public int findPsik (String s){
        int count1 =0 ;
        int count2 = 0;
        int i;
        for(i=0; i<s.length(); i++){
           if(s.charAt(i)=='('){
               count1++;
           }
           else if(s.charAt(i) == ')'){
               count2++;
           }
          else if(count1 == 1+count2 && s.charAt(i)== ','){
               break;
           }
        }
        return i;
    }

    public String toString(){
        String s="";
        s+= this.op + "(" + this.left + "," + this.right + ")";
        return s;
    }

    public function copy() {
        complexFunction cf=new complexFunction();
        this.right=cf.right;
        this.left=cf.left;
        this.op=cf.op;
        return cf;
    }
    public boolean equals(Object obj){
        return false;
    }

    public void plus(function f1){
        complexFunction left= new complexFunction(this.left, this.op, this.right);
        this.op = Operation.Plus;
        this.left=left;
        this.right=f1;
    }
    public void mul(function f1){
        complexFunction left= new complexFunction(this.left, this.op, this.right);
        this.op = Operation.Times;
        this.left=left;
        this.right=f1;
    }

    public void div(function f1){
        complexFunction left= new complexFunction(this.left, this.op, this.right);
        this.op = Operation.Divid;
        this.left=left;
        this.right=f1;
    }

    public void max(function f1){
        complexFunction left= new complexFunction(this.left, this.op, this.right);
        this.op = Operation.Max;
        this.left=left;
        this.right=f1;
    }

    public void min(function f1){
        complexFunction left= new complexFunction(this.left, this.op, this.right);
        this.op = Operation.Min;
        this.left=left;
        this.right=f1;
    }

    public void comp(function f1){
        complexFunction left= new complexFunction(this.left, this.op, this.right);
        this.op = Operation.Comp;
        this.left=left;
        this.right=f1;
    }

    public function left(){
        return this.left;
    }

    public function right(){
        return this.right;
    }
    public Operation Op(String s) {
        switch (s) {
            case "plus":
                return op.Plus;
            case "mul":
                return op.Times;
            case "div":
                return op.Divid;
            case "max":
                return op.Max;
            case "min":
                return op.Min;
            case "comp":
                return op.Comp;
            default:
                return op.Error;
        }
    }

//    public complexFunction(function f1 , String op, function f2 ){
//        switch (op){
//            case "plus":
//                this.root = new Node(null , Operation.Plus , null , null , null);
//                break;
//            case "div":
//                this.root = new Node(null , Operation.Divid , null , null , null);
//                break;
//            case "max":
//                this.root = new Node(null , Operation.Max , null , null , null);
//                break;
//            case "min":
//                this.root = new Node(null , Operation.Min , null , null , null);
//                break;
//            case "comp":
//                this.root = new Node(null , Operation.Comp , null , null , null);
//                break;
//            case "mul":
//                this.root = new Node(null , Operation.Times , null , null , null);
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + op);
//        }
//        Node left = new Node(f1 , null ,  null , null , this.root);
//        Node right = new Node(f2 , null , null , null , this.root);
//        this.root.setLeft(left);
    //       this.root.setRight(right);
    //left.setParent(this.root);
    //right.setParent(this.root);
//    }
//
//    public complexFunction(String s){
//        int i = s.indexOf('(');
//        if (i == -1){
//            Polynom p = new Polynom(s);
//            new complexFunction(p);
//        }
//        //s = s.substring(i+1);
//        String s2 = "";
//        int j = 0;
//        int counter = 0;
//        while(s.charAt(j) == 'p' || s.charAt(j) == 'd' || s.charAt(j)==  'm' || s.charAt(j) == 'c' || s.charAt(j) == 'n') {
//            s2 = s.substring(j, i);
//            switch (s2) {
//                case "plus":
//                    this.op = Operation.Plus;
//                    break;
//                case "div":
//                    this.op = Operation.Divid;
//                    break;
//                case "max":
//                    this.op = Operation.Max;
//                    break;
//                case "min":
//                    this.op = Operation.Min;
//                    break;
//                case "comp":
//                    this.op = Operation.Comp;
//                    break;
//                case "mul":
//                    this.op = Operation.Times;
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + op);
//            }
//            if (this.root == null) {
//                Node n = new Node(null , op , null , null , null);
//                this.root = n;
//                counter ++;
//            } else {
//                Node n = new Node(null , op , null , null , this.root);
//                this.root.setLeft(n);
//                this.root = this.root.getLeft();
//                counter++;
//            }
//            s = s.substring(i + 1);
//            i = s.indexOf('(');
//        }
//        i = s.indexOf(',');
//        String s3 = s.substring(0, i);
//        Polynom f1 = new Polynom(s3);
//        Node n = new Node(f1 , null , null , null , null);
//        this.root.setLeft(n);
//        s = s.substring(i+1);
//        while(!s.equals("")) {
//            i = s.indexOf(')');
//            s3 = s.substring(0, i);
//            Polynom f2 = new Polynom(s3);
//            if(this.root.getRight() != null){
//                this.root = this.root.getParent();
//                Node r = new Node(f2 , null , null , null ,this.root);
//                this.root.setRight(r);
//            }else {
//                Node r1 = new Node(f2 , null , null , null , this.root);
//                this.root.setRight(r1);
//            }
//            int t = s.indexOf(',');
//            if(t == -1){
//                s = "";
//            }
//            s = s.substring(t+1);
//        }
//    }
//
//    public double f(double x) {
//        if (this.root.getF() != null) {
//            return this.root.getF().f(x);
//        }
//        switch (this.root.getOp()) {
//            case Plus:
//                return ((this.root.getLeft().getF().f(x)) + (this.root.getRight().getF().f(x)));
//            case Divid:
//                return ((this.root.getLeft().getF().f(x)) / (this.root.getRight().getF().f(x)));
//            case Max:
//                return Math.max((this.root.getLeft().getF().f(x)), (this.root.getRight().getF().f(x)));
//            case Min:
//                return Math.min((this.root.getLeft().getF().f(x)), (this.root.getRight().getF().f(x)));
//            case Comp:
//                return this.root.getLeft().getF().f(this.root.getRight().getF().f(x));
//            case Times:
//                return ((this.root.getLeft().getF().f(x)) * (this.root.getRight().getF().f(x)));
//            case None:
//                return this.root.getLeft().getF().f(x);
//            case Error:
//                return 0;
//        }
//        return 0;
//    }
//
//    public String print(Node root , String s){
//        s += this.root.getOp();
//        s +="(";
//        //System.out.print(this.root.getOp() + "(");
//        if(this.root.getLeft().getOp() == null){
//            String s1 = print_function(this.root , s);
//            s =s1;
//        }else {
//           s += print(this.root.getLeft(), s);
//           this.root = this.root.getParent();
//            if (this.root.getRight().getF() != null) {
//                s += ",";
//                s += this.root.getRight().getF();
//                s += ")";
//                //System.out.println(this.root.getRight().getF());
//            }
//        }
//        return s;
//    }
//
//    public String print_function(Node root , String s){
//        s += this.root.getLeft().getF();
//        s += ",";
//        s += this.root.getRight().getF();
//        s += ")";
//        //System.out.print(this.root.getLeft().getF() + ",");
//        //System.out.println(this.root.getRight().getF() + ")");
//        return  s;
//    }
//
//    public String toString(){
//        String ans = "";
//       ans += print(this.root , ans);
//       // System.out.println(ans);
//        return ans;
//    }

//    public String chekEnum(){
//        switch(this.op){
//            case Plus:
//                return "plus";
//            case Divid:
//                return "divid";
//            case Max:
//                return "max";
//            case Min:
//                return "min";
//            case Comp:
//                return "comp";
//            case Times:
//                return "times";
//            case None:
//                return "none";
//            case Error:
//                return "error";
//        }
//        return null;
//    }

//    public void x(String s){
//        int i = s.indexOf('(');
//        if (i == -1){
//            Polynom p = new Polynom(s);
//            new complexFunction(p);
//        }
//        //s = s.substring(i+1);
//        String s2 = "";
//        int j = 0;
//        int counter = 0;
//        while(s.charAt(j) == 'p' && s.charAt(j) == 'd' && s.charAt(j)==  'm' && s.charAt(j) == 'c' && s.charAt(j) == 'n') {
//            s2 = s.substring(j, i);
//            switch (s2) {
//                case "plus":
//                    this.op = Operation.Plus;
//                    break;
//                case "div":
//                    this.op = Operation.Divid;
//                    break;
//                case "max":
//                    this.op = Operation.Max;
//                    break;
//                case "min":
//                    this.op = Operation.Min;
//                    break;
//                case "comp":
//                    this.op = Operation.Comp;
//                    break;
//                case "mul":
//                    this.op = Operation.Times;
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + op);
//            }
//            if (this.root == null) {
//                this.root.setOp(op);
//                counter ++;
//            } else {
//                this.root.getLeft().setOp(op);
//                this.root = this.root.getLeft();
//                counter++;
//            }
//            s = s.substring(i + 1);
//            i = s.indexOf('(');
//        }
//        i = s.indexOf(',');
//        String s3 = s.substring(0, i);
//        Polynom f1 = new Polynom(s3);
//        this.root.getLeft().setF(f1);
//        s = s.substring(i+1);
//        while(!s.equals("")) {
//            i = s.indexOf(')');
//            s3 = s.substring(0, i);
//            Polynom f2 = new Polynom(s3);
//            if(this.root.getRight().getF() != null){
//                this.root = this.root.getParent();
//                this.root.getRight().setF(f2);
//            }else {
//                this.root.getRight().setF(f2);
//            }
//            int t = s.indexOf(',');
//            if(t == -1){
//                s = "";
//            }
//            s = s.substring(t+1);
//        }
//    }

//    public function initFromString(String s){
//        complexFunction c = new complexFunction(s);
//        return c;

//        int i = s.indexOf('(');
//        if (i == -1){
//            Polynom p = new Polynom(s);
//            return p;
//        }
//        String s1 = s.substring(i+1);
//        String s2 = s.substring(0 , i);
//        if(s1.charAt(0) != 'p' && s1.charAt(0) != 'd' && s1.charAt(0) !=  'm' && s1.charAt(0) != 'c' && s1.charAt(0) != 'n'){
//            int foo = s1.indexOf(',');
//            String s3 = s1.substring(0 , foo);
//            Polynom f1 = new Polynom(s3);
//            int foo2 = s1.indexOf(')');
//            String s4 = s1.substring(foo+1 , foo2);
//            Polynom f2 = new Polynom(s4);
//           complexFunction c = new complexFunction(f1 , f2 , s2);
//            return c;
//        }
//       return initFromString(s1);
//        String arr[] = new String[];
//        int i = s.indexOf('(');
//        arr[0] = s.substring(0 , i);
//        int j = 1;
//        while (s.charAt(i+1) == 'p' || s.charAt(i+1) == 'd' || s.charAt(i+1) ==  'm' || s.charAt(i+1) == 'c' || s.charAt(i+1) == 'n'){
//            String s1 = s.substring(i+1);
//            int t = s1.indexOf('(');
//            arr[j] = s1.substring(i+1 , t);
//            j++; i++;
//        }

}
