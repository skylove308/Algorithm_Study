package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_3308 {
	static int[] dp;

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

			dp = new int[N + 1];
			int len = 0;
			for (int i = 0; i < N; i++) {
				if (arr[i] > dp[len]) {
					dp[++len] = arr[i];
				} else {
					int key = binary_search(1, len, arr[i]);
					dp[key] = arr[i];
				}
			}

			sb.append("#" + tc + " " + len + "\n");
		}
		System.out.println(sb);
	}

	static int binary_search(int left, int right, int key) {
		int mid = (left + right) / 2;
		while (left < right) {
			mid = (left + right) / 2;
			if (dp[mid] < key) {
				left = mid + 1;
			} else {
				right = mid;
			}

		}

		return right;
	}
}
