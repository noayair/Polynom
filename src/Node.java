public class Node {
    private function f;
    private Operation op;
    private Node left;
    private Node right;
    private Node parent;

    public Node (function f , Operation op , Node left , Node right , Node parent){
        this.f = f;
        this.op = op;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public function getF() {
        return f;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Operation getOp() {
        return op;
    }

    public Node getParent(){
        return parent;
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

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
