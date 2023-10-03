import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Exercise21_06Extra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine().toLowerCase();

        Map<Character, Integer> letterCount = new HashMap<>();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
            }
        }

        TreeMap<Character, Integer> sortedLetterCount = new TreeMap<>((a, b) -> {
            int countComparison = letterCount.get(a).compareTo(letterCount.get(b));
            if (countComparison == 0) {
                return a.compareTo(b);
            }
            return countComparison;
        });

        sortedLetterCount.putAll(letterCount);

        System.out.println("");
        for (Map.Entry<Character, Integer> entry : sortedLetterCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
