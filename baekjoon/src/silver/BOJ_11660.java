package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		for(int i=0; i<n; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				if(j==0) {
					arr[i][j] = Integer.parseInt(st2.nextToken());
				}
				else {
					arr[i][j] = arr[i][j-1] + Integer.parseInt(st2.nextToken());
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<m; i++) {
			StringTokenizer st3 = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st3.nextToken());
			int y1 = Integer.parseInt(st3.nextToken());
			int x2 = Integer.parseInt(st3.nextToken());
			int y2 = Integer.parseInt(st3.nextToken());
			
			int sum = 0;
			if(y1==1) {
				for(int j=x1-1; j<x2; j++) {
					sum += arr[j][y2-1];
				}
				
			}
			else {
				for(int j=x1-1; j<x2; j++) {
					sum += arr[j][y2-1]-arr[j][y1-2];
				}
			}
			sb.append(sum+"\n");
			
		}
		System.out.println(sb);
		
		

		
	}
}
