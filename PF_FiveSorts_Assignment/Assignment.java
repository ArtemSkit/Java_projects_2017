/**
 * <h1>CSCI 2336 Programming Fundamentals III Fall 2017</h1>
 * <p>
 * Assignment 5
 *
 * @author Artem Skitenko
 * @version 1.0
 * @since 10/23/2017
 */


public class Assignment {
    interface SortFunc {
        void run(int[] arr);
    }

    static int[] arr50 = new int[50], arr100 = new int[100], tempArr50 = new int[50], tempArr100 = new int[100];

    public static void main(String[] args) {
        //Create two arrays with random elements
        for (int i = 0, rand; i < 100; i++) {
            if (i < arr50.length) {
                rand = (int) (Math.random() * 1001);
                arr50[i] = rand;
            }
            rand = (int) (Math.random() * 1001);
            arr100[i] = rand;
        }
        //Output the header of the table
        System.out.println("\t\t\t\t\t+------------+-------------------------------------------------------------------+");
        System.out.println("\t\t\t\t\t|            |           Time to sort the array of N elements 10 times (ns)      |");
        System.out.println("\t\t\t\t\t|  Sort Type |---------------------------------+---------------------------------+");
        System.out.println("\t\t\t\t\t|            |            50 elements          |           100 elements          |");
        System.out.println("\t\t\t\t\t+------------+---------------------------------+---------------------------------+");
        //Create an instance of class to load the static functions
        new Heap<>();
        new FiveSorts();
        System.gc();
        //Call function that measures time and calls sort functions.
        runSort(" Bubble    ", FiveSorts::bubbleSort);
        runSort(" Selection ", FiveSorts::selectionSort);
        runSort(" Insertion ", FiveSorts::insertionSort);
        runSort(" Merge     ", FiveSorts::mergeSort);
        runSort(" Heapsort  ", Heap::sort);
        runSort(" Quicksort ", FiveSorts::qickSort);

        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\033[0;4m\033[0;1m" + "Analysis\n" + "\u001B[0m\033[0;0m");
        System.out.println("\t\t\t\t\tBubble sort has the worst time and space complexity because it has " +
                "\n\t\t\t\t\tto swap neighbor elements instead of shifting them.");
        System.out.println("\t\t\t\t\tThe next slowest is selection sort, followed by a little faster " +
                "\n\t\t\t\t\tinsertion sort. However, this is true only for the small array of 50 " +
                "\n\t\t\t\t\telements. When the size of the array grows to 100 elements, selection " +
                "\n\t\t\t\t\tsort becomes faster than insertion sort.");
        System.out.println("\t\t\t\t\tMerge sort showed better results than any of the O(N^2) searches. ");
        System.out.println("\t\t\t\t\tNext one is heapsort. It has relatively the same space and time " +
                "\n\t\t\t\t\tcomplexity as merge sort.");
        System.out.println("\t\t\t\t\tThe fastest one is quicksort. It has the best application of " +
                "\n\t\t\t\t\t\"Divide and conquer\" design paradigm out of all examined sorts.");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\033[0;4m\033[0;1m" + "Conclusion\n" + "\u001B[0m\033[0;0m");
        System.out.println("\t\t\t\t\tIt is better to use quicksort for sorting arrays.");
    }

    static void runSort(String msg, SortFunc sort) {
        long startTime, endTime, avg50 = 0, avg100 = 0;
        //Run sorts 10 times
        for (int i = 0; i < 10; i++) {
            //Set temporary array to array with random numbers
            System.arraycopy(arr50, 0, tempArr50, 0, 50);
            startTime = System.nanoTime();
            sort.run(tempArr50);
            endTime = System.nanoTime();
            avg50 += endTime - startTime;
            System.arraycopy(arr100, 0, tempArr100, 0, 100);
            startTime = System.nanoTime();
            sort.run(tempArr100);
            endTime = System.nanoTime();
            avg100 += endTime - startTime;
        }
        System.out.format("\t\t\t\t\t| " + msg + "|%10.3d              |%10.3d              |\n", avg50 / 10, avg100 / 10);
        System.out.println("\t\t\t\t\t+------------+---------------------------------+---------------------------------+");
    }
}
