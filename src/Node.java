public class Node {
   private Node left;
   private Node right;
   private function f;
   private Operation op;

   Node (function f, Operation op, Node left, Node right){
       this.f = f;
       this.op = op;
       this.left= left;
       this.right= right;
   }
   public function getF(){
       return f;
   }
   public Node getLeft(){
       return left;
   }
    public Node getRight() {
        return right;
    }
    public Operation getOp(){
       return op;
    }

    public void setF(function f) {
        this.f = f;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setOp(Operation op) {
        this.op = op;
    }

}

