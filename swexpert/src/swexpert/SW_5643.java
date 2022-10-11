package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_5643 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			int[][] adj = new int[N + 1][N + 1];
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adj[a][b] = 1;
			}

			boolean[] visited;
			Queue<Integer> q = new LinkedList<>();
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				int cnt = 0;
				visited = new boolean[N + 1];
				q.add(i);
				visited[i] = true;
				while (!q.isEmpty()) {
					int p = q.poll();
					for (int j = 1; j <= N; j++) {
						if (adj[p][j] == 1 && !visited[j]) {
							q.add(j);
							visited[j] = true;
							cnt++;
						}

					}
				}

				visited = new boolean[N + 1];
				q.add(i);
				visited[i] = true;
				while (!q.isEmpty()) {
					int p = q.poll();
					for (int j = 1; j <= N; j++) {
						if (adj[j][p] == 1 && !visited[j]) {
							q.add(j);
							visited[j] = true;
							cnt++;
						}

					}
				}

				if (cnt == N - 1)
					ans++;
			}
			sb.append("#" + tc + " " + ans +"\n");
		}
		System.out.println(sb);
	}
}
