class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int totalGas = 0;
        int totalCost = 0;

        int currentGas = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {

            int gain = gas[i] - cost[i];

            totalGas += gas[i];
            totalCost += cost[i];

            currentGas += gain;

            // Current starting point cannot work
            if (currentGas < 0) {
                start = i + 1;
                currentGas = 0;
            }
        }

        // Not enough gas overall
        if (totalGas < totalCost) {
            return -1;
        }

        return start;
    }
}