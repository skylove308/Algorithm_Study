package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2133 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		if (N >= 2) {
			dp[0] = 1;
			dp[2] = 3;
			for (int i = 4; i <= N; i += 2) {
				dp[i] += 3 * dp[i - 2];
				for (int j = 4; j <= i; j += 2) {
					dp[i] += 2 * dp[i - j];
				}
			}
		}

		System.out.println(dp[N]);

	}
}
