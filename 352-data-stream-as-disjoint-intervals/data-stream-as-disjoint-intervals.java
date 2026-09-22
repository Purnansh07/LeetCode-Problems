class SummaryRanges {

    private final TreeMap<Integer, Integer> map;

    public SummaryRanges() {
        map = new TreeMap<>();
    }

    public void addNum(int value) {

        // Find interval on the left
        Map.Entry<Integer, Integer> left = map.floorEntry(value);

        // Already contained in an interval
        if (left != null && left.getValue() >= value) {
            return;
        }

        // Find interval on the right
        Map.Entry<Integer, Integer> right = map.ceilingEntry(value);

        boolean mergeLeft =
            left != null && left.getValue() + 1 >= value;

        boolean mergeRight =
            right != null && right.getKey() - 1 <= value;

        if (mergeLeft && mergeRight) {

            // [leftStart, leftEnd] + value + [rightStart, rightEnd]
            int leftStart = left.getKey();
            int rightEnd = right.getValue();

            map.remove(leftStart);
            map.remove(right.getKey());

            map.put(leftStart, rightEnd);

        } else if (mergeLeft) {

            // Extend left interval
            int leftStart = left.getKey();

            map.remove(leftStart);
            map.put(leftStart, value);

        } else if (mergeRight) {

            // Extend right interval backwards
            int rightEnd = right.getValue();

            map.remove(right.getKey());
            map.put(value, rightEnd);

        } else {

            // Create a new interval
            map.put(value, value);
        }
    }

    public int[][] getIntervals() {
        int[][] ans = new int[map.size()][2];

        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans[i][0] = entry.getKey();
            ans[i][1] = entry.getValue();
            i++;
        }

        return ans;
    }
}