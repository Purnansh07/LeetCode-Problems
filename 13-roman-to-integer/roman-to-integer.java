class Solution {
    public int romanToInt(String s) {
        int[] value = new int[128];
        value['I'] = 1;
        value['V'] = 5;
        value['X'] = 10;
        value['L'] = 50;
        value['C'] = 100;
        value['D'] = 500;
        value['M'] = 1000;

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int curr = value[s.charAt(i)];

            if (i < s.length() - 1 && curr < value[s.charAt(i + 1)]) {
                result -= curr;
            } else {
                result += curr;
            }
        }

        return result;
    }
}