package Lista9;

public class Node {
    private String value;
    private Node leftChild, rightChild;

    public Node(String value){
        this.value = value;
        leftChild = null; 
        rightChild = null;
    }
    
    public String getValue(){
        return value;
    }
    public void setValue(String value){
        this.value = value;
    }

    public void setLeftChild(Node value){
        leftChild = value;
    }

    public void setRightChild(Node value){
        rightChild = value;
    }

    public Node getLeftChild(){
        return leftChild;
    }

    public Node getRightChild(){
        return rightChild;
    }
}
