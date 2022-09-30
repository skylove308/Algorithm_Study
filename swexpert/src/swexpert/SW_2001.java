package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_2001 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     int t = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
	     for(int test_case=1; test_case<=t; test_case++) {
	    	 StringTokenizer st = new StringTokenizer(br.readLine());
		     int n = Integer.parseInt(st.nextToken());
		     int m = Integer.parseInt(st.nextToken());
		     int[][] arr = new int[n][n];
		     for(int i=0; i<n; i++) {
		    	 StringTokenizer st2 = new StringTokenizer(br.readLine());
		    	 for(int j=0; j<n; j++) {
		    		 arr[i][j] = Integer.parseInt(st2.nextToken());
		    	 }
		     }
		     int max = 0;
		     for(int i=0; i<n-m+1; i++) {
		    	 for(int j=0; j<n-m+1; j++) {
		    		 int sum = 0;
		    		 for(int k=0; k<m; k++) {
		    			 for(int l=0; l<m; l++) {
		    				 sum += arr[i+k][j+l];
		    			 }
		    			 
		    		 }
		    		 max = max > sum ? max : sum;
		    	 }
		     }
		     System.out.println("#"+test_case+" "+max);
	     }
	     
	}
}
