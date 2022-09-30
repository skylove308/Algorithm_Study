package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_2805 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		for(int test_case=0; test_case<t; test_case++) {
			int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			int[][] farm = new int[n][n];
			
			for(int i=0; i<n; i++) {
				String st = new StringTokenizer(br.readLine()).nextToken();
				for(int j=0; j<n; j++) {
					farm[i][j] = st.charAt(j) - '0';
				}
			}
			
			int sum = 0;
			for(int i=0; i<n/2; i++) {
				for(int j=n/2-i; j<=n/2+i; j++) {
					sum += farm[i][j];
				}
			}
			for(int i=n/2; i<n; i++) {
				for(int j=i-n/2; j<n+n/2-i; j++) {
					sum += farm[i][j];
				}
			}
			
			System.out.println("#"+(test_case+1)+" "+sum);
		}

	}
}
