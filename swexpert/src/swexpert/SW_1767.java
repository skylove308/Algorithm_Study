package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_1767 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, size, min;
	static int[][] map;
	static ArrayList<Point> core;
	static boolean[] possible_core;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			core = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						core.add(new Point(i, j));
				}
			}
			
			size = core.size();
			possible_core = new boolean[size];
			min = Integer.MAX_VALUE;
			for (int i = size; i >= 0; i--) {
				comb(0, 0, i);
				if (min != Integer.MAX_VALUE)
					break;
			}

			sb.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sb);
	}

	static void comb(int idx, int cnt, int n) {
		if (cnt == n) {
			dfs(0, 0);
			return;
		}

		for (int i = idx; i < size; i++) {
			possible_core[idx] = true;
			comb(i + 1, cnt + 1, n);
			possible_core[idx] = false;
		}
	}

	static void dfs(int idx, int cnt) {
		if (idx == size) {
			min = Math.min(min, cnt);
			return;
		}

		if (!possible_core[idx]) {
			dfs(idx + 1, cnt);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int x = core.get(idx).x;
			int y = core.get(idx).y;
			boolean isPossible = true;
			int len = 0;
			while (true) {
				x += dx[i];
				y += dy[i];
				if (x < 0 || y < 0 || x >= N || y >= N)
					break;
				if (map[x][y] == 1 || map[x][y] == 2) {
					isPossible = false;
					break;
				}
				map[x][y] = 2;
				len++;
			}
			if (isPossible)
				dfs(idx + 1, cnt + len);
			while (true) {
				x -= dx[i];
				y -= dy[i];
				if (map[x][y] == 1)
					break;
				map[x][y] = 0;
			}
		}

	}
}
