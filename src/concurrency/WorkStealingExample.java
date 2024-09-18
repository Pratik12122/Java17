package concurrency;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class WorkStealingExample extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10;  // Task size threshold for splitting
    private int[] array;
    private int start, end;

    public WorkStealingExample(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            // Base case: directly compute the sum for small tasks
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Recursive case: split the task into two subtasks
            int mid = (start + end) / 2;
            WorkStealingExample leftTask = new WorkStealingExample(array, start, mid);
            WorkStealingExample rightTask = new WorkStealingExample(array, mid, end);

            // Fork the left task and compute the right task in parallel
            leftTask.fork();  // Submit the left task to another thread
            int rightResult = rightTask.compute();  // Compute the right task
            int leftResult = leftTask.join();  // Wait for the left task to complete

            // Combine the results
            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        // Create a large array of integers
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;  // Fill the array with values from 1 to 100
        }

        // Create a ForkJoinPool with multiple threads (typically equal to the number of CPU cores)
        ForkJoinPool pool = new ForkJoinPool();

        // Create the root task to sum the array
        WorkStealingExample task = new WorkStealingExample(array, 0, array.length);

        // Invoke the task and get the result
        int result = pool.invoke(task);

        // Output the result
        System.out.println("Sum of array: " + result);  // Expected output: 5050
    }
}

