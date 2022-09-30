package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109 {
	static int r, c, ans = 0; // 세로 크기, 가로 크기, 정답
	static char[][] map; // 전체 맵
	static boolean[][] visited; // 방문 여부 확인
	static boolean check_pipeline; // pipeline이 완성 되었는지 여부 확인
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		visited = new boolean[r][c];
		for(int i=0; i<r; i++) {
			String s = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='x') {
					visited[i][j]=true; // x는 지나갈 수 없는 공간이므로 visit체크
				}
			}
		}
		// 문제 입력 끝
		for(int i=0; i<r; i++) {
			check_pipeline = false; // false로 초기화
			dfs(i, 0, 1); //라인 r개에 대하여 bfs 실행 
		}
		System.out.println(ans);
		
	}
	
	static void dfs(int x, int y, int cnt) {
		visited[x][y] = true;
		if(cnt==c) {
			ans++;
			check_pipeline = true; // dfs가 한번 성공한 순간 나머지는 pass
		}
		for(int i=0; i<3; i++) {
			if(check_pipeline) continue;
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||ny<0||nx>=r||ny>=c) continue;
			if(visited[nx][ny]) continue;
			dfs(nx, ny, cnt+1);
			
		}
		
	}
}
