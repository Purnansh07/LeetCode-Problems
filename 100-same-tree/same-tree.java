/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 */

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both are empty
        if (p == null && q == null) {
            return true;
        }

        // One is empty
        if (p == null || q == null) {
            return false;
        }

        // Values differ
        if (p.val != q.val) {
            return false;
        }

        // Check both subtrees
        return isSameTree(p.left, q.left)
            && isSameTree(p.right, q.right);
    }
}