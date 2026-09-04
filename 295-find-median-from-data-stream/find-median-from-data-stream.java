class MedianFinder {

    // Smaller half
    private PriorityQueue<Integer> left =
        new PriorityQueue<>(Collections.reverseOrder());

    // Larger half
    private PriorityQueue<Integer> right =
        new PriorityQueue<>();

    public MedianFinder() {
    }

    public void addNum(int num) {
        // Put num into left first
        left.offer(num);

        // Move largest element of left to right
        right.offer(left.poll());

        // Keep left size >= right size
        if (right.size() > left.size()) {
            left.offer(right.poll());
        }
    }

    public double findMedian() {
        if (left.size() > right.size()) {
            return left.peek();
        }

        return ((long) left.peek() + right.peek()) / 2.0;
    }
}