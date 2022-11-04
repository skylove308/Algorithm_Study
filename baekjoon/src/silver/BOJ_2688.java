package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2688 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			long[][] dp = new long[N+1][10];
			for(int i=0; i<=9; i++) {
				dp[1][i] = 1;
			}
			for(int i=2; i<=N; i++) {
				for(int j=0; j<=9; j++) {
					for(int k=0; k<=j; k++) {
						dp[i][j] += dp[i-1][k];
					}
				}
			}
			
			long ans = 0;
			for(int i=0; i<=9; i++) {
				ans += dp[N][i];
			}
			
			sb.append(ans+"\n");
		}
		System.out.println(sb);
	}
}
