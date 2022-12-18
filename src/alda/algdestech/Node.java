package alda.algdestech;

/*

 */

public class Node implements Comparable<Node> {
    private int frequency;


    public Node(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }



    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.getFrequency(), o.getFrequency());
    }
}
