package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_6808 {
	static int[] a, b, result;
	static boolean[] used;
	static int win, lose;
	public static void main(String[] args) throws NumberFormatException, IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     int t = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
	     for(int test_case=1; test_case<=t; test_case++) {
	    	 StringTokenizer st = new StringTokenizer(br.readLine());
	    	 a = new int[9];
	    	 b = new int[9];
	    	 result = new int[9];
	    	 used = new boolean[9];
	    	 for(int i=0; i<9; i++) {
	    		 a[i] = Integer.parseInt(st.nextToken());
	    	 }
	    	 int index = 0;
	    	 for(int i=1; i<=18; i++) {
	    		 boolean check = false;
	    		 for(int j: a) {
	    			 if(i==j) check = true;
	    		 }
	    		 if(!check) {
	    			 b[index++] = i;
	    		 }
	    		 
	    	 }
	    	 win = 0;
	    	 lose = 0;
	    	 perm(0);
	    	 System.out.println("#"+test_case+" "+win+" "+lose);
	    	 
	     }
	}
	
	static void perm(int index) {
		if(index==9) {
			int sum = 0;
   		 	int sum2 = 0;
			for(int i=0; i<9; i++) {
				if(a[i] > result[i]) {
					sum += a[i] + result[i];
				}
				if(a[i] < result[i]) {
					sum2 += a[i] + result[i];
				}
			}
			if(sum>sum2) win++;
			if(sum<sum2) lose++;
			
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(used[i]) continue;
			result[index] = b[i];
			used[i]=true;
			perm(index+1);
			used[i]=false;
		}
		
	}
	
}
