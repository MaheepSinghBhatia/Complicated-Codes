import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
public class Exercise19_09 {
    public static void main(String[] args) {
    	Integer[] numbers = new Integer[10];
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 10 integers: ");
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = input.nextInt();
		}
		
        ArrayList<Integer> integerList =
                new ArrayList<>(Arrays.asList(numbers));
		
        sort(integerList);
        System.out.println("The sorted numbers are " + integerList);
    }

    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
        boolean loop = true;
        while (loop) {
            loop = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    loop = true;
                    E temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                }
            }
        }
    }
}