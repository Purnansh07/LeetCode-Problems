class Solution {

    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<int[]> events = new ArrayList<>();

        // Create events
        for (int[] b : buildings) {
            int left = b[0];
            int right = b[1];
            int height = b[2];

            // Start event
            events.add(new int[]{left, -height});

            // End event
            events.add(new int[]{right, height});
        }

        // Sort events
        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }

            return Integer.compare(a[1], b[1]);
        });

        List<List<Integer>> result = new ArrayList<>();

        // Max heap
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        // Ground level
        maxHeap.offer(0);

        int previousHeight = 0;

        for (int[] event : events) {

            int x = event[0];
            int value = event[1];

            if (value < 0) {
                // Building starts
                maxHeap.offer(-value);

            } else {
                // Building ends
                maxHeap.remove(value);
            }

            int currentHeight = maxHeap.peek();

            // Skyline changed
            if (currentHeight != previousHeight) {

                result.add(
                    Arrays.asList(x, currentHeight)
                );

                previousHeight = currentHeight;
            }
        }

        return result;
    }
}