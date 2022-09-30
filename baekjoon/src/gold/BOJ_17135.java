package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135 {
	static int n, m, d, max = 0; // 세로 크기, 가로 크기, 정답
	static int[][] map, map_origin; // 전체 맵
	static int[] dx = {0, -1, 0};
	static int[] dy = {-1, 0, 1};
	static int[] possible_y;
	static boolean[][] destroyed;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로축 크기
		m = Integer.parseInt(st.nextToken()); // 가로축 크기
		d = Integer.parseInt(st.nextToken()); // 궁수의 거리
		map = new int[n][m];
		map_origin = new int[n][m]; // 맵 원본을 저장할 변수
		for(int i=0; i<n; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
				map_origin[i][j] = map[i][j];
			}
		}
		// 문제 입력 끝
		possible_y = new int[3];
		comb(0, 0);
		System.out.println(max);
	}

	static void comb(int idx, int cnt) { // m개의 자리 중 3개를 선택하는 조합의 가지 수
		if(cnt == 3) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					map[i][j] = map_origin[i][j];
				}
			}
			int count = 0;
			for(int i=n; i>=1; i--) {
				count += destroy_enemy(i, possible_y);
			}
			max = Math.max(max, count);
			return;
		}
		for(int i=idx; i<m; i++) {
			possible_y[cnt] = i;
			comb(i+1, cnt+1);
		}
	}
	
	static int destroy_enemy(int x, int[] y) {
		int cnt=0;
		destroyed = new boolean[n][m];
		for(int i=0; i<3; i++) {
			visited = new boolean[n][m];
			if(x<n) {
				for(int j=0; j<m; j++) {
					visited[x][j] = true;
				}
			}
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[]{x, y[i], 1});
			loop:
			while(!q.isEmpty()) {
				int[] p = q.poll();
				if(p[2]>d) break;
				for(int j=0; j<3; j++) {
					int nx = p[0] + dx[j];
					int ny = p[1] + dy[j];
					if(nx<0||ny<0||nx>=n||ny>=m) continue;
					if(visited[nx][ny]) continue;
					if(destroyed[nx][ny]) break loop;
					if(map[nx][ny]==1) {
						map[nx][ny]=0;
						destroyed[nx][ny] = true;
						cnt++;
						break loop;
					}
					visited[nx][ny] = true;
					q.add(new int[]{nx, ny, p[2]+1});
				}
			}
		}
		return cnt;
	}
	
}
