import java.util.Arrays;

public class QuickSort {
    public void sort(int[] numbers) {
        sort(numbers, 0, numbers.length - 1);
    }

    private void sort(int[] numbers, int start, int end) {
        if (start < end) {
            int p = partition(numbers, start, end);
            sort(numbers, start, p - 1);
            sort(numbers, p + 1, end);
        }
    }

    private int partition(int[] numbers, int start, int end) {
        int pivot = numbers[end];
        int x = start - 1;
        for (int i = start; i < end; i++) {
            if (numbers[i] < pivot) {
                x++;
                swap(numbers, x, i);
            }
        }
        swap(numbers, x + 1, end);
        return x + 1;
    }

    private void swap(int[] numbers, int j, int k) {
        int temp = numbers[j];
        numbers[j] = numbers[k];
        numbers[k] = temp;
    }


    public static void main(String args[]) {
        QuickSort quickSort = new QuickSort();
        int[] numbers = new int[]{4,5,33,17,3,21,1,16};
        quickSort.partition(numbers, 5, 6);
        System.out.println(Arrays.toString(numbers));
    }

}
