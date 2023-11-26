package src.ait.map;

import java.util.*;

public class DigitsFreqAppl {
    static Random random = new Random();

    public static void main(String[] args) {

        int[] numbers = geneNum(1_000_000);

        Map<Integer, Integer> digitFrequency = calcDigitFrequency(numbers);

        System.out.println("===== Unsorted =====");
        printDigitFrequency(digitFrequency);

        System.out.println("===== Sorted by value desc =====");
        printSortedDigitFrequency(digitFrequency);
    }

    private static int[] geneNum(int count) {
        int[] numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = random.nextInt(Integer.MAX_VALUE) + 1;
        }
        return numbers;
    }

    private static Map<Integer, Integer> calcDigitFrequency(int[] numbers) {
        Map<Integer, Integer> digitFrequency = new HashMap<>();
        for (int number : numbers) {
            while (number > 0) {
                int digit = number % 10;
                digitFrequency.merge(digit, 1, (oldValue, newValue) -> oldValue + newValue);
                number /= 10;
            }
        }
        return digitFrequency;
    }

    private static void printDigitFrequency(Map<Integer, Integer> digitFrequency) {
        for (Map.Entry<Integer, Integer> entry : digitFrequency.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    private static void printSortedDigitFrequency(Map<Integer, Integer> digitFrequency) {
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(digitFrequency.entrySet());
        entries.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

        for (Map.Entry<Integer, Integer> entry : entries) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
