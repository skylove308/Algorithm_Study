package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ_3687 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		long[] dp = new long[101];
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 7;
		dp[4] = 4;
		dp[5] = 2;
		dp[6] = 6;
		dp[7] = 8;
		dp[8] = 10;
		for(int i=9; i<=100; i++) {
			dp[i] = Long.MAX_VALUE;
		}
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			if (N < 9)
				sb.append(dp[N] + " ");
			else {
				for (int i = 9; i <= N; i++) {
					if (dp[i] == Long.MAX_VALUE)
						dp[i] = Collections.min(Arrays.asList(dp[i - 7] * 10 + 8, dp[i - 6] * 10 + 0,
								dp[i - 5] * 10 + 2, dp[i - 4] * 10 + 4, dp[i - 3] * 10 + 7, dp[i - 2] * 10 + 1));
				}
				sb.append(dp[N] + " ");
			}

			StringBuilder max = new StringBuilder();
			int cnt = 0;
			while (true) {
				if (N == 2) {
					max.insert(0, 1);
					break;
				}

				if (N == 3) {
					max.insert(0, 7);
					break;
				}

				max.append(1);
				cnt++;
				N -= 2;
			}
			sb.append(max + "\n");
		}
		
		System.out.println(sb);
	}
}
