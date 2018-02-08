public final class Heap<E extends Comparable<E>> {

    private java.util.ArrayList<E> list = new java.util.ArrayList<>();

    public static void sort(int[] arr) {

        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int currentIndex = i; // The index of the last node
            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                // Swap if the current object is greater than its parent
                if (arr[currentIndex] > arr[parentIndex]) {
                    int temp = arr[currentIndex];
                    arr[currentIndex] = arr[parentIndex];
                    arr[parentIndex] = temp;
                } else
                    break; // the tree is a heap now
                currentIndex = parentIndex;
            }
        }
        length--;
        for (int i = 0, j, size; i < length; i++) {
            size = length - i;
            int temp = arr[0];
            arr[0] = arr[size];
            arr[size] = temp;
            j = 0;

            int leftChildIndex, rightChildIndex;
            while (j < size) {
                leftChildIndex = 2 * j + 1;
                rightChildIndex = 2 * j + 2;

                // Find the maximum between two children
                if (leftChildIndex >= size - 1) break; // The tree is a heap
                int maxIndex = leftChildIndex;
                if (rightChildIndex < size) {
                    if (arr[maxIndex] < arr[rightChildIndex]) maxIndex = rightChildIndex;
                }
                // Swap if the current node is less than the maximum
                if (arr[j] < arr[maxIndex]) {
                    int temp1 = arr[maxIndex];
                    arr[maxIndex] = arr[j];
                    arr[j] = temp1;
                    j = maxIndex;
                } else
                    break; // The tree is a heap
            }
        }
    }

    /**
     * Create a default heap
     */
    public Heap() {
    }

    /**
     * Create a heap from an array of objects
     *
     * @param objects
     */
    public Heap(E[] objects) {
        for (E object : objects) {
            add(object);
        }
    }

    /**
     * Add a new object into the heap
     *
     * @param newObject
     */
    public void add(E newObject) {
        list.add(newObject); // Append to the heap
        int currentIndex = list.size() - 1; // The index of the last node

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            // Swap if the current object is greater than its parent
            if (list.get(currentIndex).compareTo(
                    list.get(parentIndex)) > 0) {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            } else
                break; // the tree is a heap now

            currentIndex = parentIndex;
        }
    }

    /**
     * Remove the root from the heap
     */
    public E remove() {
        if (list.size() == 0) return null;

        E removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            // Find the maximum between two children
            if (leftChildIndex >= list.size()) break; // The tree is a heap
            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (list.get(maxIndex).compareTo(
                        list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }
            // Swap if the current node is less than the maximum
            if (list.get(currentIndex).compareTo(
                    list.get(maxIndex)) < 0) {
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            } else
                break; // The tree is a heap
        }
        return removedObject;
    }

    /**
     * Get the number of nodes in the tree
     *
     * @return
     */
    public int getSize() {
        return list.size();
    }
}