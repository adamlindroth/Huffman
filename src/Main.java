import alda.algdestech.EncodedMessage;
import alda.algdestech.HuffmanCoder;

public class Main {

    public static void main(String[] args) {
        HuffmanCoder coder = new HuffmanCoder();
        EncodedMessage<alda.algdestech.Node, String> s = coder.encode("karafanakarafanarasa");
        System.out.println(s.message);
        System.out.println(coder.decode(s));

    }
}
