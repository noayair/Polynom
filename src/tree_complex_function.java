public abstract class tree_complex_function implements complex_function {
    Node left, right;
    Operation root;
    public class Node{
        function left, right;
        Operation root;
        function f;
        Node(function f){
            this.f = f;
            left = this.f;
            right = null;
            root = null;
        }
    }
    tree_complex_function(Node f , Operation op){
        if(this.left == null){
            this.left = f;
        }else{
           this.right = f;
        }
        this.root = op;
    }

    public void add(Node f , Operation op){

    }
}
