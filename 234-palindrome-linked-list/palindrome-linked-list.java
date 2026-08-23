class Solution {
    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        // 1. Find middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. For odd length, skip the middle node
        if (fast != null) {
            slow = slow.next;
        }

        // 3. Reverse second half
        ListNode secondHalf = reverse(slow);

        // 4. Compare both halves
        ListNode firstHalf = head;
        ListNode reversed = secondHalf;

        boolean palindrome = true;

        while (reversed != null) {

            if (firstHalf.val != reversed.val) {
                palindrome = false;
                break;
            }

            firstHalf = firstHalf.next;
            reversed = reversed.next;
        }

        // 5. Restore the list
        reverse(secondHalf);

        return palindrome;
    }

    private ListNode reverse(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            ListNode next = curr.next;

            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}