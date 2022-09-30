package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10026 {
	static int n;
	static char[][] arr, arr2;
	static boolean[][] visited, visited2;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		arr2 = new char[n][n];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				arr[i][j] = s.charAt(j);
				arr2[i][j] = arr[i][j];
				if(arr2[i][j]=='R')
					arr2[i][j]='G';
			}
		}
		visited = new boolean[n][n];
		visited2 = new boolean[n][n];
		int cnt = 0;
		int cnt2 = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(visited[i][j]) continue;
				dfs(i, j, arr[i][j]);
				cnt++;
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(visited2[i][j]) continue;
				dfs2(i, j, arr2[i][j]);
				cnt2++;
			}
		}
		
		System.out.println(cnt+" "+cnt2);
	}
	
	static void dfs(int x, int y, char c) {
		visited[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0||ny<0||nx>=n||ny>=n) continue;
			if(visited[nx][ny]) continue;
			if(arr[nx][ny]==c)
				dfs(nx, ny, c);
		}
	}
	
	static void dfs2(int x, int y, char c) {
		visited2[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0||ny<0||nx>=n||ny>=n) continue;
			if(visited2[nx][ny]) continue;
			if(arr2[nx][ny]==c)
				dfs2(nx, ny, c);
		}
	}
}
