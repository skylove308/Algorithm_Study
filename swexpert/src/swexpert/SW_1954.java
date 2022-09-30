package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SW_1954 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        int[] n_arr = new int[t];
        for(int test_case=0; test_case<t; test_case++) {
            n_arr[test_case] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        }
        for(int test_case=0; test_case<t; test_case++) {
            int n = n_arr[test_case];
            int[][] arr = new int[n][n];
            int num = 1;
            for(int i=0; i<n-i; i++) {
                for(int j=i; j<n-i-1; j++) {
                    arr[i][j] = num++;
                     
                }
                for(int j=i; j<n-i-1; j++) {
                    arr[j][n-i-1] = num++;
                     
                }
                for(int j=n-i-1; j>i; j--) {
                    arr[n-i-1][j] = num++;
                     
                }
                for(int j=n-i-1; j>i; j--) {
                    arr[j][i] = num++;
                     
                }   
            }
            if(n%2!=0) {
                arr[n/2][n/2] = n*n;
            }
            System.out.println("#"+(test_case+1));
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    System.out.print(arr[i][j]+" ");    
                }
                System.out.println();
            }
        }
    }
}