/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */

class Solution {
    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        // Find length
        int length = 0;
        ListNode current = head;

        while (current != null) {
            length++;
            current = current.next;
        }

        // Dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Bottom-up merge sort
        for (int size = 1; size < length; size *= 2) {

            ListNode prev = dummy;
            current = dummy.next;

            while (current != null) {

                // First half
                ListNode left = current;

                // Second half
                ListNode right = split(left, size);

                // Next part
                current = split(right, size);

                // Merge left and right
                ListNode merged = merge(left, right);

                // Connect merged list
                prev.next = merged;

                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }

        return dummy.next;
    }

    // Split first 'size' nodes and return the remaining list
    private ListNode split(ListNode head, int size) {

        if (head == null) {
            return null;
        }

        ListNode current = head;

        for (int i = 1; i < size && current.next != null; i++) {
            current = current.next;
        }

        ListNode second = current.next;
        current.next = null;

        return second;
    }

    // Merge two sorted lists
    private ListNode merge(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {

            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }

            current = current.next;
        }

        if (l1 != null) {
            current.next = l1;
        }

        if (l2 != null) {
            current.next = l2;
        }

        return dummy.next;
    }
}