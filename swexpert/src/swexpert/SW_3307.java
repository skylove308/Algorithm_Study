package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_3307 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int[] dp = new int[N];
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
				for (int j = 0; j < i; j++) {
					if (arr[j] < arr[i]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
					}
				}
			}

			int max = 0;
			for (int i = 0; i < N; i++) {
				max = Math.max(max, dp[i]);
			}

			sb.append("#" + tc + " " + max + "\n");
		}
		System.out.println(sb);
	}
}
