class Solution {

    public TreeNode lowestCommonAncestor(
            TreeNode root,
            TreeNode p,
            TreeNode q) {

        // Base case
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left =
            lowestCommonAncestor(root.left, p, q);

        TreeNode right =
            lowestCommonAncestor(root.right, p, q);

        // p and q are in different subtrees
        if (left != null && right != null) {
            return root;
        }

        // Return whichever side contains p or q
        return left != null ? left : right;
    }
}