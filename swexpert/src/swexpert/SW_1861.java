package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1861 {
	static int n, cnt, min_num;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=t; test_case++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			visited = new boolean[n][n];
			for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max = 0;
			int min = Integer.MAX_VALUE;
			cnt = 1; 
			min_num = Integer.MAX_VALUE;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(visited[i][j]) continue;
					dfs(i, j);
					if(max < cnt) {
						max = cnt;
						min = min_num;
					}
					else if(max == cnt) {
						if(min > min_num) {
							min = min_num;
						}
					}
					cnt = 1;
					min_num = Integer.MAX_VALUE;
				}
			}
			System.out.println("#"+test_case+" "+min+" "+max);
			
		}
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		min_num = min_num < arr[x][y] ? min_num : arr[x][y]; 
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0||ny<0||nx>=n||ny>=n) continue;
			if(visited[nx][ny]) continue;
			if(arr[nx][ny]==arr[x][y]+1||arr[nx][ny]==arr[x][y]-1) {
				dfs(nx, ny);
				cnt++;
			}
		}

	}
}
