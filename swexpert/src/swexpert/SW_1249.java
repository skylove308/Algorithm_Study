package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SW_1249 {
	static class Point {
		int x, y, cost;

		public Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}

			boolean[][] visited = new boolean[n][n];
			PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> {
				return o1.cost - o2.cost;
			});

			int[][] dist = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dist[i][j] = Integer.MAX_VALUE;
				}
			}

			pq.add(new Point(0, 0, map[0][0]));
			dist[0][0] = map[0][0];
			while (!pq.isEmpty()) {
				Point p = pq.poll();
				if (p.x == n - 1 && p.y == n - 1) 
					break;
				
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if (nx < 0 || ny < 0 || nx >= n || ny >= n)
						continue;
					if(visited[nx][ny])
						continue;
					if (dist[p.x][p.y] + map[nx][ny] > dist[nx][ny])
						continue;
					dist[nx][ny] = dist[p.x][p.y] + map[nx][ny];
					pq.add(new Point(nx, ny, dist[nx][ny]));
					visited[nx][ny] = true;
				}
			}

			sb.append("#" + tc + " " + dist[n - 1][n - 1] + "\n");

		}

		System.out.println(sb);
	}
}
