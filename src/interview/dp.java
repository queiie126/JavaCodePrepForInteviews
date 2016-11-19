package interview;

/*
 This class tries out a couple dp problem
 @HW
 */
public class dp{
    
    public static void main(String[] args){
        int[] arr = new int[]{8,15,3,7};
       // int[] arr2 = new int[]{20,30,2,2,2,10};
        int[] arr3 = new int[]{2,2,2,2};
      /*  System.out.println(maxgame(arr));
        System.out.println(maxgame(arr2));
        System.out.println(maxgame(arr3));*/
        System.out.println(knapsack(arr, arr3, 5, 4));
        System.out.println(knapsack(arr3, arr, 5, 4));
    }
    //the rule of the game: 2 players, each get to remove one number from end or front,
    //each player plays optimally, return maximum sum value one removes
    public static int maxgame(int[] arr){
    //    cache = new int[arr.length][arr.length];
        return maxgame(arr, 0, arr.length-1);
    }
    //the key to this question is to store calculated value in cache
    private int cache;
    private static int maxgame(int[] arr, int lo, int hi){
        if(lo==hi) return arr[lo];
        if(lo==hi-1) return Math.max(arr[lo], arr[hi]);
        return Math.max(arr[lo]+Math.min(maxgame(arr, lo+2, hi), maxgame(arr, lo+1,hi-1)),
                        arr[hi]+Math.min(maxgame(arr, lo, hi-2), maxgame(arr, lo+1,hi-1)));
    }
    //the rule for this specific knapsack problem is:
    //any item has a weight and a value, determine the maximum value a sack can handle
    public static int knapsack(int[] weightarr, int[] valarr, int maxweight, int n){
        int[][] dp = new int[n+1][maxweight+1];
        for(int i = 0;i<dp.length;i++){
            for(int j = 0;j<dp[0].length;j++){
                if(i==0||j==0) dp[i][j] = 0;
                else if(weightarr[i-1]<=j){//**
                    dp[i][j] = Math.max(valarr[i-1]+dp[i-1][j-weightarr[i-1]],
                                        dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
