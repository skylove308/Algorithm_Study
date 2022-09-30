package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485 {
	static class Point implements Comparable<Point> {
		int x, y, cost;

		public Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n;
		int t = 1;
		while ((n = Integer.parseInt(br.readLine())) != 0) {
			int[][] cave = new int[n][n];
			boolean[][] visited = new boolean[n][n];
			int[][] dist = new int[n][n];
			PriorityQueue<Point> link = new PriorityQueue<>();
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			link.add(new Point(0, 0, cave[0][0]));
			dist[0][0] = cave[0][0];

			while (!link.isEmpty()) {
				Point minE = link.poll();
				int px = minE.x;
				int py = minE.y;

				if (visited[px][py])
					continue;

				if (px == n - 1 && py == n - 1)
					break;

				visited[minE.x][minE.y] = true;
				for (int i = 0; i < 4; i++) {
					int nx = minE.x + dx[i];
					int ny = minE.y + dy[i];
					if (nx < 0 || ny < 0 || nx >= n || ny >= n)
						continue;
					if (visited[nx][ny])
						continue;
					if (dist[px][py] + cave[nx][ny] > dist[nx][ny])
						continue;
					dist[nx][ny] = dist[px][py] + cave[nx][ny];
					link.add(new Point(nx, ny, dist[nx][ny]));
				}

			}

			sb.append("Problem" + " " + t + ": " + dist[n - 1][n - 1] + "\n");
			t++;
		}
		
		System.out.println(sb);

	}
}
