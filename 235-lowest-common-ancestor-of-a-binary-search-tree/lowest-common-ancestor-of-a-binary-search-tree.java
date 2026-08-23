class Solution {
    public TreeNode lowestCommonAncestor(
            TreeNode root,
            TreeNode p,
            TreeNode q) {

        while (root != null) {

            if (p.val < root.val && q.val < root.val) {

                // Both are on the left
                root = root.left;

            } else if (p.val > root.val && q.val > root.val) {

                // Both are on the right
                root = root.right;

            } else {

                // They split here
                // or root is p/q
                return root;
            }
        }

        return null;
    }
}