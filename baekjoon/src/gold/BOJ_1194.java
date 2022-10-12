package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194 {
	static class Point {
		int x, y, z;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static int N, M; // 세로, 가로
	static char[][] map;
	static boolean[][][] visited; // 어떤 열쇠조합으로 도착했는지
	static Queue<Point> q;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		q = new LinkedList<>();
		visited = new boolean[N][M][1 << 6]; // 000000: 0 ~ 111111: 63 까지 사용가능한 배열 크기

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					map[i][j] = '.';
					visited[i][j][0] = true;
					q.add(new Point(i, j, 0));
				}
			}
		}
		
		System.out.println(bfs());
	}

	static int bfs() {
		int dist = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point now = q.poll();
				if (map[now.x][now.y] == '1')
					return dist;
				for (int d = 0; d < 4; d++) {
					int nx = now.x + dx[d];
					int ny = now.y + dy[d];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == '#' || visited[nx][ny][now.z])
						continue;

					// a~f : 열쇠, A~F : 문, . : 평지, 1 : 탈출구
					if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') { // 열쇠인 경우
						int newz = 1 << (map[nx][ny] - 'a');
						int nz = now.z | newz; // 기존에 가진 열쇠에 새로운 열쇠 추가
						q.add(new Point(nx, ny, nz));
						visited[nx][ny][nz] = true;
					} else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') { // 문인 경우
						int door = 1 << (map[nx][ny] - 'A');
						if ((door & now.z) > 0) { // 열쇠 가지고 있는지 체크하기
							q.add(new Point(nx, ny, now.z));
							visited[nx][ny][now.z] = true;
						}
					} else { // 평지인 경우
						q.add(new Point(nx, ny, now.z));
						visited[nx][ny][now.z] = true;
					}
				}

			}
			dist++;
		}
		
		return -1;
	}
}
