class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;

        int[] count = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';

            if (s == g) {
                // Exact match
                bulls++;
            } else {
                // If secret digit can match a previous guess digit
                if (count[s] < 0) cows++;

                // If guess digit can match a previous secret digit
                if (count[g] > 0) cows++;

                count[s]++;
                count[g]--;
            }
        }

        return bulls + "A" + cows + "B";
    }
}