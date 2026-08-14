/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *
 *     TreeNode() {}
 *     TreeNode(int val) {
 *         this.val = val;
 *     }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    private int[] preorder;
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;

        inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode build(int preStart, int preEnd,
                           int inStart, int inEnd) {

        // No nodes
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // First element of preorder is the root
        int rootValue = preorder[preStart];

        TreeNode root = new TreeNode(rootValue);

        // Find root in inorder
        int rootIndex = inorderMap.get(rootValue);

        // Number of nodes in left subtree
        int leftSize = rootIndex - inStart;

        // Build left subtree
        root.left = build(
            preStart + 1,
            preStart + leftSize,
            inStart,
            rootIndex - 1
        );

        // Build right subtree
        root.right = build(
            preStart + leftSize + 1,
            preEnd,
            rootIndex + 1,
            inEnd
        );

        return root;
    }
}