package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1012 {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] farm; // 농장의 배추지도
	static int m,n,k;
	static boolean[][] visited; // 방문한 노드인지 구분
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		for(int test_case=0; test_case<t; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			farm = new boolean[n][m];
			visited = new boolean[n][m];
			for(int i=0; i<k; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st2.nextToken());
				int x = Integer.parseInt(st2.nextToken());
				farm[x][y] = true; // 배추가 있으면 true
			}

			int cnt=0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(farm[i][j]&&!visited[i][j]) { // 배추가 있거나 방문한 노드가 아니면 dfs
						dfs(i, j);
						cnt++; // dfs 횟수체크
					}
				}
			}
			
			System.out.println(cnt);
		}
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0||ny<0||nx>=n||ny>=m||visited[nx][ny]||!farm[nx][ny]) {
				continue;
			}
			dfs(nx, ny);

			
		}	
	}
}
