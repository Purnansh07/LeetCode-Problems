class Solution {

    public int countNodes(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int height = getHeight(root);

        // Perfect binary tree
        if (height == 0) {
            return 1;
        }

        int left = 0;
        int right = (1 << height) - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (exists(mid, height, root)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Nodes above last level + nodes on last level
        return (1 << height) - 1 + left;
    }

    // Height of tree excluding the root's level
    private int getHeight(TreeNode root) {

        int height = 0;

        while (root.left != null) {
            height++;
            root = root.left;
        }

        return height;
    }

    // Check whether a node exists at index idx
    // on the last level
    private boolean exists(
        int idx,
        int height,
        TreeNode root
    ) {

        int left = 0;
        int right = (1 << height) - 1;

        for (int i = 0; i < height; i++) {

            int mid = left + (right - left) / 2;

            if (idx <= mid) {
                root = root.left;
                right = mid;
            } else {
                root = root.right;
                left = mid + 1;
            }
        }

        return root != null;
    }
}