
public class Solution {
    
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int bestProfit = 0;
        int buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                if (prices[i] - buy > bestProfit) {
                    bestProfit = prices[i] - buy;
                }
            }
        }
        return bestProfit;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
