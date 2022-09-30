package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4963 {
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[][] map; // 전체 지도
	static int w, h; // 너비, 높이
	static boolean[][] visited; // 방문한 노드인지 구분
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while(!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st = new StringTokenizer(s);
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			visited = new boolean[h][w];
			for(int i=0; i<h; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st2.nextToken());
				}
			}

			int cnt=0;
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j]==1&&!visited[i][j]) { // 배추가 있거나 방문한 노드가 아니면 dfs
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
		for(int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0||ny<0||nx>=h||ny>=w||visited[nx][ny]||map[nx][ny]==0) {
				continue;
			}
			dfs(nx, ny);
			
		}	
	}
}
