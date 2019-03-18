import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        Integer[] input = { 10, 15, 3, 7 };
        System.out.println(find(input, 5));
    }

    public static boolean find(Integer[] array, int wanted) {
        Map<Integer, Integer> iterated = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            Integer current = array[i];
            Integer complement = (Integer) wanted - current;
            Integer found = iterated.get(complement);

            if (found != null) {
                return true;
            } else {
                iterated.put(current, current);
            }
        }

        return false;
    }
}
