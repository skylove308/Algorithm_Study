package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17837 {
	static class Point {
		int n, x, y, d, prev, next;

		public Point(int n, int x, int y, int d, int prev, int next) {
			this.n = n;
			this.x = x;
			this.y = y;
			this.d = d;
			this.prev = prev;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Point [n=" + n + ", x=" + x + ", y=" + y + ", d=" + d + ", prev=" + prev + ", next=" + next + "]";
		}

	}

	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) { // 맵 정보
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] chess = new int[N + 1][N + 1]; // 말의 위치가 주어져 있는 체스판

		Point[] chessMan = new Point[K + 1]; // 체스말 K개
		chessMan[0] = new Point(0, 0, 0, 0, 0, 0);
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			chessMan[i] = new Point(i, x, y, d, 0, 0);
			chess[x][y] = i;
		}

		int time = 1;
		boolean isFinished = false;
		loop: while (time <= 1000) { // 1000 초과인 경우에는 게임이 종료되지 않는 다고 가정하고 finish
			for (int i = 1; i <= K; i++) {
				int nx = chessMan[i].x + dx[chessMan[i].d];
				int ny = chessMan[i].y + dy[chessMan[i].d];
				if (nx < 1 || ny < 1 || nx > N || ny > N || map[nx][ny] == 2) { // 맵을 벗어나거나 파란색
					chessMan[i].d = changeDirection(chessMan[i].d);
					nx = chessMan[i].x + dx[chessMan[i].d];
					ny = chessMan[i].y + dy[chessMan[i].d];
					if (nx < 1 || ny < 1 || nx > N || ny > N || map[nx][ny] == 2) { // 방향을 바꿨는데도 맵을 벗어나거나 파란색
						continue;
					}
					chessMan[i] = new Point(i, nx, ny, chessMan[i].d, chess[nx][ny], 0);
					chess[nx][ny] = i;
					System.out.println(3);
				} else if (map[nx][ny] == 0) { // 하얀색
					chessMan[chess[nx][ny]].next = i;
					chessMan[i].prev = chess[nx][ny];
					int j = i;
					while (true) {
						chessMan[j] = new Point(j, nx, ny, chessMan[j].d, chessMan[j].prev, chessMan[j].next);
						if (j == 0 || chessMan[j].next == 0) {
							chess[nx][ny] = j;
							break;
						}
						j = chessMan[j].next;
					}
					System.out.println(1);
				} else if (map[nx][ny] == 1) { // 빨간색
					chess[nx][ny] = i;
					int j = i;
					chessMan[j] = new Point(j, nx, ny, chessMan[j].d, chessMan[j].next, 0);
					while (true) {
						j = chessMan[j].prev;			
						if (j == 0 || chessMan[j].next == 0) {
							chessMan[j] = new Point(j, nx, ny, chessMan[j].d, chessMan[j].next, chessMan[j].prev);
							break;
						}	
						chessMan[j] = new Point(j, nx, ny, chessMan[j].d, chessMan[j].next, chessMan[j].prev);
					}					
					System.out.println(2);
				}
				
				for (int k = 1; k <= K; k++) {
					System.out.println(chessMan[k]);
				}
				
				for (int j = 1; j <= K; j++) {
					int cnt = 1;
					int k = j;
					while (true) {
						if (chessMan[k].next == 0)
							break;
						cnt++;
						k = chessMan[k].next;
						//System.out.println(4);
					}
					if (cnt >= 4) {
						isFinished = true;
						break loop;
					}
				}

			}
			time++;
		}

		if (isFinished)
			System.out.println(time);
		else
			System.out.println(-1);

	}

	static int changeDirection(int d) {
		if (d == 1)
			return 2;
		else if (d == 2)
			return 1;
		else if (d == 3)
			return 4;
		else if (d == 4)
			return 3;

		return -1;
	}

	static void print() {

	}
}
