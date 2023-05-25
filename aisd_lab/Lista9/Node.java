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

    public void setLeftChild(String value){
        leftChild = new Node(value);
    }

    public void setRightChild(String value){
        rightChild = new Node(value);
    }

    public Node getLeftChild(){
        return leftChild;
    }

    public Node getRightChild(){
        return rightChild;
    }
}
