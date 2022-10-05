package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2624 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] coin = new int[K][2];
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			coin[i][0] = p;
			coin[i][1] = n;
		}
		
		int[] dp = new int[T+1];
		for(int i=0; i<K; i++) {
			for(int j=1; j<=coin[i][1]; j++) {
				for(int k=coin[i][0]*j; k<=T; k++) {
					dp[k] += dp[k-coin[i][0]*j];
				}
				
			}
			
		}
		
	}
}
