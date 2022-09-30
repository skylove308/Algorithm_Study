package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1987 {
	static int r, c, max;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		int[] alphabet = new int[26];
		for(int i=0; i<r; i++) {
			String s = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		dfs(0, 0, 1, alphabet);
		System.out.println(max);
		
	}
	
	static void dfs(int x, int y, int cnt, int[] alphabet) {
		max = Math.max(max, cnt);
		alphabet[cnt-1] = map[x][y];
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0||ny<0||nx>=r||ny>=c) continue;
			boolean check = false;
			for(int j: alphabet) {
				if(j==map[nx][ny]) {
					check = true;
					break;
				}
			}
			if(check) continue;
			dfs(nx, ny, cnt+1, alphabet);
			alphabet[cnt] = 0;
		}
	}
}
