// Good morning! Here's your coding interview problem for today.
// This problem was recently asked by Google.
// Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
// For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
// Bonus: Can you do this in one pass?


import java.util.HashMap;
import java.util.Map;

public class Problem1 {

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
