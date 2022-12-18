package alda.algdestech;

import java.util.*;

public class HuffmanCoder {

    //Hashmap som används för att räkna förekomsterna av varje bokstav.
    private HashMap<Character, Integer> frequencies = new HashMap<>();
    /*Kö där Node-objekt placeras, ordnade efter frequency.
        Gör det lätt att bygga ett träd genom att skapa ett subträd av de två första objekten
        som man sedan stoppar tillbaks i kön.
     */
    private PriorityQueue<Node> queue = new PriorityQueue<>();

    //Map som kopplar varje bokstav till en kod. Används för att bygga det kodade meddelandet
    private Map<Character, String> encodingTable = new HashMap<>();






    public EncodedMessage<Node, String> encode(String msg) {
        Node header;
        calculateFrequencies(msg);
        enqueue();
        buildTree();
        fillCodeTable(queue.peek(), "");
        StringBuilder encodedMsg = new StringBuilder();
        for(char c : msg.toCharArray()){
            encodedMsg.append(encodingTable.get(c));
        }
        EncodedMessage<Node, String> message =  new EncodedMessage<>(queue.poll(), encodedMsg.toString());
        clearData();
        return message;
    }

    public String decode(EncodedMessage<?, ?> msg) {
        String message = (String)msg.message;
        Node root = (Node) msg.header;
        Node current = (Node)msg.header;
        StringBuilder stringBuilder = new StringBuilder();

        for(char c : message.toCharArray()){
            if(current instanceof Parent){
                if(c == '0'){
                    current = ((Parent) current).getLeftChild();
                } else {
                    current = ((Parent) current).getRightChild();
                }
            }
            if(current instanceof Leaf){
                stringBuilder.append(((Leaf) current).getCharacter());
                current = root;
            }
        }
        return stringBuilder.toString();
    }


    /*
        Går igenom varje bokstav i strängen och lägger dem i en hash-map tillsammans med en räknare.
     */
    private void calculateFrequencies(String msg) {
        for (char c : msg.toCharArray()) {
            if (!frequencies.containsKey(c)) {
                frequencies.put(c, 1);
            } else {
                frequencies.put(c, frequencies.get(c) + 1);
            }
        }
    }

    private void enqueue(){
        for(char c : frequencies.keySet()){
            queue.add(new Leaf(c, frequencies.get(c)));
        }
    }

    private void buildTree(){

        while(queue.size() > 1){
            Node leaf_one = queue.poll();
            Node leaf_two = queue.poll();
            int totalFrequency = leaf_one.getFrequency() + leaf_two.getFrequency();
            Node parent;
            if(leaf_one.getFrequency() < leaf_two.getFrequency()){
                parent = new Parent(totalFrequency, leaf_two, leaf_one);
            } else {
                parent = new Parent(totalFrequency, leaf_one, leaf_two);
            }
            queue.add(parent);
        }
    }

    private void fillCodeTable(Node n, String s){
        if(n instanceof Leaf){
            encodingTable.put(((Leaf) n).getCharacter(), s);
            return;
        }
        else if(n instanceof Parent){
            fillCodeTable(((Parent) n).getLeftChild(), s.concat("0"));
            fillCodeTable(((Parent) n).getRightChild(), s.concat("1"));
        }
    }


    private void clearData(){
        frequencies.clear();
        queue.clear();
        encodingTable.clear();
    }


}
