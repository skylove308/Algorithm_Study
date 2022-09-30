package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1463 {
	static int N, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

//		dp();
//		bfs();
		dfs(N, 0);
		System.out.println(min);
	}

	static void dp() {
		int[] dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			dp[i] = Integer.MAX_VALUE;
		}

		dp[1] = 0;

		for (int i = 2; i <= N; i++) {
			if (i % 3 == 0)
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			if (i % 2 == 0)
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			dp[i] = Math.min(dp[i], dp[i - 1] + 1);
		}

		System.out.println(dp[N]);
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		int cnt = 0;
		loop: while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int x = q.poll();
				if (x == 1)
					break loop;
				if (x % 3 == 0)
					q.add(x / 3);
				if (x % 2 == 0)
					q.add(x / 2);
				q.add(x - 1);
			}
			cnt++;
		}
		System.out.println(cnt);
	}

	static void dfs(int x, int cnt) {
		if (x == 1) {
			min = Math.min(min, cnt);
			return;
		}
		if (cnt >= min)
			return;

		if (x % 3 == 0)
			dfs(x / 3, cnt + 1);
		if (x % 2 == 0)
			dfs(x / 2, cnt + 1);
		dfs(x - 1, cnt + 1);
	}
}
