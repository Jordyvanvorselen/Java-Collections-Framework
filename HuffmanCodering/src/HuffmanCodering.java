import java.util.*;

/**
 * Created by sander on 14-3-2016.
 */
public class HuffmanCodering {

    public static HashMap<Character, String> letterWithCodeList = new HashMap<>();
    public static String message = "Jordy is the king and wollum is a scrub";

    public static void main(String[] args) {
        HuffmanCodering huff = new HuffmanCodering();
        huff.Execute();
    }

    public static PriorityQueue<HuffKnoop> priorityQueue;

    /**
     * Executes all methods
     */
    public void Execute() {
        Map<String, Integer> hashMap = getFrequentie(message);
        priorityQueue = sortFrequentie(hashMap);
        createHuffmanTree(priorityQueue);
        createCodes(priorityQueue.peek(), "");
        decode(encode(letterWithCodeList, message), priorityQueue.peek());
        System.out.println("Done.");
    }

    /**
     * Step 1
     * Gets the frequency of each letter from the input
     *
     * @param userInput the user input
     * @return a HashMap with the letter as key and frequency as value
     */
    public static HashMap<String, Integer> getFrequentie(String userInput) {
        //Using an HashMap because this has a Key/Value, so you can put every word the user put in as a key and the value is an integer that
        //gets incremented if the same word is added again.
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        //Every word is used as a key to check if it already exists in the HashMap,
        //if it does not yet exist we can add it to the HashMap with a value of 1.
        //If it DOES exist we increment the existing value.
        for (String s : userInput.split("")) {
            if (!s.equals("")) {
                Integer frequency = hashMap.get(s);

                if (frequency != null) {
                    hashMap.put(s, frequency + 1);
                } else {
                    hashMap.put(s, 1);
                }
            }
        }

        return hashMap;
    }

    /**
     * Step 2
     * Sorts the HashMap on frequency
     *
     * @param hashMap the HashMap containing the letter and frequency
     * @return A sorted LinkedList in ascending order
     */
    public static PriorityQueue<HuffKnoop> sortFrequentie(Map<String, Integer> hashMap) {
        List<HuffKnoop> huffKnopen = new LinkedList<>();
        PriorityQueue<HuffKnoop> priorityQueueKek;

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            String key = (String) pair.getKey();
            int value = (int) pair.getValue();

            huffKnopen.add(new HuffKnoop(key.charAt(0), value));
        }

        Comparator<HuffKnoop> comparator = new HuffKnoopCompare();
        priorityQueueKek = new PriorityQueue<>(huffKnopen.size(), comparator);

        for (HuffKnoop huff : huffKnopen) {
            priorityQueueKek.add(huff);
        }
        return priorityQueueKek;
    }

    /**
     * Step 3
     * Creates the Huffman Tree
     */
    public static HuffKnoop createHuffmanTree(PriorityQueue<HuffKnoop> q) {
        HuffKnoop huff1 = q.poll();
        HuffKnoop huff2 = q.poll();

        int frequency = huff1.getFrequency() + huff2.getFrequency();
        q.add(new HuffKnoop(frequency, huff1, huff2));

        if (q.size() > 1) {
            createHuffmanTree(q);
        }

        return q.peek();
    }

    /**
     * Step 4
     * Creates a code for each node
     */
    public static HashMap<Character, String> createCodes(HuffKnoop knoop, String code) {
        if (knoop != null)
        {
            createCodes(knoop.getLeftChild(), code + "0");
            createCodes(knoop.getRightChild(), code + "1");
            if (knoop.getLeftChild() == null && knoop.getRightChild() == null)
                letterWithCodeList.put(knoop.getCharacter(), code);
        }
        return letterWithCodeList;
    }

    public static String encode(HashMap<Character, String> codeWithString, String message)
    {
        StringBuilder sb = new StringBuilder();
        for (Character c : message.toCharArray())
        {
            sb.append(codeWithString.get(c));
        }
        return sb.toString();
    }

    public static String decode(String message, HuffKnoop rootKnoop)
    {
        return rootKnoop.decode(message);
    }
}
