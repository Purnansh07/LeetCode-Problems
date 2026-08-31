class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;
        int last = -1;

        int minDist = Integer.MAX_VALUE;

        int index = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr.next != null) {

            ListNode next = curr.next;

            // Check if curr is a critical point
            boolean critical =
                    (curr.val > prev.val && curr.val > next.val) ||
                    (curr.val < prev.val && curr.val < next.val);

            if (critical) {

                // First critical point
                if (first == -1) {
                    first = index;
                }

                // Calculate distance from previous critical point
                if (last != -1) {
                    minDist = Math.min(minDist, index - last);
                }

                last = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Fewer than two critical points
        if (first == -1 || first == last) {
            return new int[]{-1, -1};
        }

        int maxDist = last - first;

        return new int[]{minDist, maxDist};
    }
}