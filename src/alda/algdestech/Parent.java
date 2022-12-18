package alda.algdestech;

public class Parent extends Node{

    private Node leftChild;
    private Node rightChild;

    public Parent(int frequency, Node leftChild , Node rightChild) {
        super(frequency);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public boolean hasLeftChild(){
        return leftChild != null;
    }

    public boolean hasRightChild(){
        return rightChild != null;
    }
}
