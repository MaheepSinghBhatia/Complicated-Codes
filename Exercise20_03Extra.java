import java.util.PriorityQueue;
import java.util.Scanner;

public class Exercise20_03Extra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PriorityQueue<Integer> queue1 = new PriorityQueue<>();
        PriorityQueue<Integer> queue2 = new PriorityQueue<>();

        System.out.print("Enter integers for priority queue 1: ");
        String line1 = scanner.nextLine();
        System.out.print("Enter integers for priority queue 2: ");
        String line2 = scanner.nextLine();

        addToIntQueue(line1, queue1);
        addToIntQueue(line2, queue2);

        PriorityQueue<Integer> union = new PriorityQueue<>(queue1);
        union.addAll(queue2);

        PriorityQueue<Integer> difference = new PriorityQueue<>(queue1);
        difference.removeAll(queue2);

        PriorityQueue<Integer> intersection = new PriorityQueue<>(queue1);
        intersection.retainAll(queue2);

        System.out.println("The union of the two priority queues is: ");
        displayQueue(union);

        System.out.println("The difference of the two priority queues is: ");
        displayQueue(difference);

        System.out.println("The intersection of the two priority queues is: ");
        displayQueue(intersection);
    }

    private static void addToIntQueue(String input, PriorityQueue<Integer> queue) {
        String[] numbers = input.split(" ");
        for (String numStr : numbers) {
            try {
                int num = Integer.parseInt(numStr);
                queue.add(num);
            } catch (NumberFormatException e) {
                
            }
        }
    }

    private static void displayQueue(PriorityQueue<Integer> queue) {
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();
    }
}
