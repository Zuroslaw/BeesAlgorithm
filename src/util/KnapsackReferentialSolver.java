package util;

class KnapsackReferentialSolver
{

    // A utility function that returns maximum of two integers
    static int max(int a, int b) { return (a > b)? a : b; }

    // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSack(int W, int wt[], int val[], int n)
    {
        // Base Case
        if (n == 0 || W == 0)
            return 0;

        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (wt[n-1] > W)
            return knapSack(W, wt, val, n-1);

            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
        else return max( val[n-1] + knapSack(W-wt[n-1], wt, val, n-1),
                knapSack(W, wt, val, n-1)
        );
    }


    // Driver program to test above function
    public static void main(String args[])
    {
        int val[] = new int[]{67, 52, 15, 14, 46, 56, 82, 62, 61, 154, 45, 145, 112, 27, 101, 53, 112, 175, 142, 41, 99, 143, 144, 33, 20, 20, 21, 8, 89, 127, 162, 160, 32, 113, 109, 38, 94, 77, 21, 23};
        int wt[] = new int[]{100, 46, 43, 21, 25, 81, 82, 63, 42, 16, 57, 46, 83, 31, 68, 92, 77, 51, 97, 45, 19, 90, 19, 29, 22, 31, 29, 18, 54, 36, 95, 41, 36, 51, 33, 27, 22, 52, 19, 91};
        int  W = 400;
        int n = val.length;
        Timer.tic();
        System.out.println(knapSack(W, wt, val, n));
        Timer.toc();
    }
}
/*This code is contributed by Rajat Mishra */
