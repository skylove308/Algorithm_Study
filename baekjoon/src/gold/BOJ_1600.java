package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600 {
	static class Point {
		int x, y, cnt, h; // h는 원숭이가 말의 동작을 한 횟수

		public Point(int x, int y, int cnt, int h) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.h = h;
		}
	}

	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = { -1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dy = { 0, 0, -1, 1, -2, -1, 1, 2, 2, 1, -1, -2 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[H][W][K + 1];
		System.out.println(bfs());

	}

	static int bfs() {
		if(W==1&&H==1)
			return 0;
		Queue<Point> q = new LinkedList<>();
		visited[0][0][0] = true;
		q.add(new Point(0, 0, 0, 0));
		int cnt = -1;
		loop: while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 12; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				int nh = p.h;
				if (nx < 0 || ny < 0 || nx >= H || ny >= W)
					continue;
				if (map[nx][ny] == 1)
					continue;
				if (i < 4) {
					if (nx == H - 1 && ny == W - 1) {
						cnt = p.cnt + 1;
						break loop;
					}
					if (visited[nx][ny][nh])
						continue;
					visited[nx][ny][nh] = true;
					q.add(new Point(nx, ny, p.cnt + 1, nh));
				} else {
					if (nh == K)
						continue;
					if (nx == H - 1 && ny == W - 1) {
						cnt = p.cnt + 1;
						break loop;
					}
					if (visited[nx][ny][nh + 1])
						continue;
					visited[nx][ny][nh + 1] = true;
					q.add(new Point(nx, ny, p.cnt + 1, nh + 1));
				}
			}
		}
		return cnt;
	}

}
