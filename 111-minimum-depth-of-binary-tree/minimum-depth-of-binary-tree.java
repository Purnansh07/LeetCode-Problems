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
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Leaf node
        if (root.left == null && root.right == null) {
            return 1;
        }

        // Only right subtree exists
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }

        // Only left subtree exists
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        // Both subtrees exist
        return 1 + Math.min(
            minDepth(root.left),
            minDepth(root.right)
        );
    }
}