class Solution {
    public int numWays(String[] words, String target) {
        int[][] lookUp = new int[words[0].length()][26];
        int mod = (int)1e9 + 7;
        int res = 0;

        for(int i = 0; i < words.length; i++) {
            for(int j = 0; j < words[0].length(); j++) {
                lookUp[j][words[i].charAt(j) - 'a']++;
            }
        }

        
        int wordLength = words[0].length();
        long[][] dp = new long[target.length()][wordLength];
        for(int i = 0; i < wordLength; i++) {
            dp[0][i] = (i > 0 ? dp[0][i - 1] : 0) + lookUp[i][target.charAt(0) - 'a'];
        }
        for(int i = 1; i < target.length(); i++) {
            int ch = target.charAt(i) - 'a';
            for(int j = i; j < wordLength; j++) {
                dp[i][j] = ((dp[i - 1][j - 1] * lookUp[j][ch]) % mod + dp[i][j - 1]) % mod; 
            }
        }

        
        return (int)dp[target.length() - 1][wordLength - 1];
    }
}