class Solution {
    public boolean isValidSerialization(String preorder) {
        int slots = 1; // Slot for the root

        for (String node : preorder.split(",")) {
            // Current node occupies one slot
            slots--;

            // More nodes than available positions
            if (slots < 0) {
                return false;
            }

            // Non-null node creates two child slots
            if (!node.equals("#")) {
                slots += 2;
            }
        }

        // Every slot must be filled
        return slots == 0;
    }
}