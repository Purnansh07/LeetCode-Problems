/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    private Map<Integer, Integer> inorderMap = new HashMap<>();
    private int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;

        // Store each value's index in inorder
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(
            0,
            inorder.length - 1,
            0,
            postorder.length - 1
        );
    }

    private TreeNode build(int inStart, int inEnd,
                           int postStart, int postEnd) {

        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // Last element of postorder is the root
        int rootValue = postorder[postEnd];

        TreeNode root = new TreeNode(rootValue);

        // Find root in inorder
        int rootIndex = inorderMap.get(rootValue);

        // Number of nodes in left subtree
        int leftSize = rootIndex - inStart;

        // Build left subtree
        root.left = build(
            inStart,
            rootIndex - 1,
            postStart,
            postStart + leftSize - 1
        );

        // Build right subtree
        root.right = build(
            rootIndex + 1,
            inEnd,
            postStart + leftSize,
            postEnd - 1
        );

        return root;
    }
}