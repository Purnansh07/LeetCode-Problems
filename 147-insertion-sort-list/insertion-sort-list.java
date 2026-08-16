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
    public ListNode insertionSortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        // Dummy node before the sorted list
        ListNode dummy = new ListNode(0);

        ListNode current = head;

        while (current != null) {

            // Save next unsorted node
            ListNode next = current.next;

            // Find insertion position
            ListNode prev = dummy;

            while (prev.next != null &&
                   prev.next.val < current.val) {
                prev = prev.next;
            }

            // Insert current
            current.next = prev.next;
            prev.next = current;

            // Move to next unsorted node
            current = next;
        }

        return dummy.next;
    }
}