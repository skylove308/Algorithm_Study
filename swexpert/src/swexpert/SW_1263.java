package swexpert;

import java.util.Scanner;

public class SW_1263 {
    static int[][] map;
    static long[][] dp;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int TC = sc.nextInt();
        
        for(int tc=1; tc<=TC; tc++) {
            int row = sc.nextInt();
            
            map = new int[row][row];
            dp = new long[row][row];
            
            for(int i=0; i<row; i++) {
                for(int j=0; j<row; j++) {
                    map[i][j] = sc.nextInt();
                    if(map[i][j]==0)
                        dp[i][j] = INF;
                    else
                        dp[i][j]=1;
                    
                    if(i==j)
                        dp[i][j]=0;
                }
            }
            

            for(int k=0; k<row; k++) {
                for(int i=0; i<row; i++) {
                    for(int j=0; j<row; j++) {
                        if(dp[i][j]>dp[i][k]+dp[k][j]) {
                            dp[i][j] = dp[i][k]+dp[k][j];
                        }
                    }
                }
            }

            
            int min=INF;
            for(int i=0; i<row; i++) {
                int tmp=0;
                for(int j=0; j<row; j++) {
                    tmp += dp[i][j];
                }
                if(min > tmp)
                    min = tmp;
            }
            
            System.out.println("#"+tc+" "+min);
        }
                
    }
}