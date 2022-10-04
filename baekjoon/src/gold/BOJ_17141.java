package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17141 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, empty_space, min = Integer.MAX_VALUE;
	static ArrayList<Point> virus;
	static int[] virus_idx;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<>();
		int cnt = 0;
		empty_space = N * N;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virus.add(new Point(i, j));
				if (map[i][j] == 1)
					empty_space--;
			}
		}
		virus_idx = new int[M];
		comb(0, 0);
		if(min==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	static void comb(int idx, int cnt) {
		if(cnt == M) {		
			Queue<Point> q = new LinkedList<>();
			boolean[][] visited = new boolean[N][N];
			int virus_cnt = 0;
			for(int i=0; i<M; i++) {
				int px = virus.get(virus_idx[i]).x;
				int py = virus.get(virus_idx[i]).y;
				q.add(new Point(px, py));
				visited[px][py] = true;
				virus_cnt++;
			}
			int time = 0;
			while(!q.isEmpty()) {
				int size = q.size();
				for(int i=0; i<size; i++) {
					Point p = q.poll();
					for(int j=0; j<4; j++) {
						int nx = p.x + dx[j];
						int ny = p.y + dy[j];
						if(nx<0||ny<0||nx>=N||ny>=N) continue;
						if(visited[nx][ny]) continue;
						if(map[nx][ny]==1) continue;
						q.add(new Point(nx, ny));
						visited[nx][ny] = true;
						virus_cnt++;
					}
				}
				time++;
			}
			if(virus_cnt==empty_space)
				min = Math.min(min, time-1);
			return;
		}
		for(int i=idx; i<virus.size(); i++) {
			virus_idx[cnt] = i;
			comb(i+1, cnt+1);
		}
	}
}
