package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1520 {
	static int N, M;
	static int[][] map, dp;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[N][M];
		dp[0][0] = 1;

		dfs(0, 0);
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println(dp[N-1][M-1]);
		
		
	}
	
	static void dfs(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0||ny<0||nx>=N||ny>=M) continue;
			if(map[x][y] <= map[nx][ny]) continue;
			dp[nx][ny] += dp[x][y];
			dfs(nx, ny);
		}
	}
}
