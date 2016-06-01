import java.util.Comparator;

/**
 * Comparator for HuffKnoop
 *
 * Created by sander on 18-3-2016.
 */
public class HuffKnoopCompare implements Comparator<HuffKnoop> {

    /**
     * Compares 2 HuffKnoop instances
     * @param o1 first HuffKnoop
     * @param o2 second HuffKnoop
     * @return 1 When o1 is greater | -1 when o2 is greater | 0 when o1 and o2 are equeal
     */
    @Override
    public int compare(HuffKnoop o1, HuffKnoop o2) {
        if (o1.getFrequency() > o2.getFrequency()) {
            return 1;
        }
        else if(o1.getFrequency() < o2.getFrequency())
        {
            return -1;
        }
        else
        {
            Character c1 = o1.getCharacter();
            Character c2 = o2.getCharacter();

            return c1.compareTo(c2);
        }
    }
}
