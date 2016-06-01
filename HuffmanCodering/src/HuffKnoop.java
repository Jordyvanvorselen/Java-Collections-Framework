/**
 * Huffknoop instance
 *
 * Created by sander on 14-3-2016.
 */
public class HuffKnoop {
    private char character;
    private int frequency;
    private HuffKnoop leftChild;
    private HuffKnoop rightChild;
    private String code;

    /**
     * Constructor for a HuffKnoop with no children
     * @param character the character
     * @param frequency the frequency of the character
     */
    public HuffKnoop(char character, int frequency)
    {
        this.setCharacter(character);
        this.setFrequency(frequency);
    }

    /**
     * Constructor for a HuffKnoop with children
     * @param frequency the frequency
     * @param leftChild First HuffKnoop
     * @param rightChild Second HuffKnoop
     */
    public HuffKnoop(int frequency, HuffKnoop leftChild, HuffKnoop rightChild) {
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public HuffKnoop getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(HuffKnoop leftChild) {
        this.leftChild = leftChild;
    }

    public HuffKnoop getRightChild() {
        return rightChild;
    }

    public void setRightChild(HuffKnoop rightChild) {
        this.rightChild = rightChild;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String decode(String message){
        StringBuilder sb = new StringBuilder();
        HuffKnoop knoop = this;
        for (int i = 0; i < message.length(); i++)
        {
            if (message.charAt(i) == '1'){
                knoop = knoop.getRightChild();
            }
            else {
                knoop = knoop.getLeftChild();
            }
            if (knoop.getLeftChild() == null && knoop.getRightChild() == null) {
                sb.append(knoop.getCharacter());
                knoop = this;
            }
        }
        System.out.println("Message was: " + sb);
        return sb.toString();
    }
}
