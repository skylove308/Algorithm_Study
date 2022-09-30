package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2667 {
	static int n, cnt;
	static char[][] arr;
	static boolean[][] visited;
	static List<Integer> count;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		visited = new boolean[n][n];
		count = new ArrayList<>();
		// 문제 입력 끝
		
		// arr의 1번 영역에 대하여 dfs 실시
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(visited[i][j]) continue; // 이미 방문한 노드면 pass
				if(arr[i][j]=='0') continue; // 0번 영역은 pass
				cnt = 0; // dfs 실행한 횟수만큼 카운트, 단지 내 집 수를 count
				dfs(i, j);
				count.add(cnt); // count리스트에 단지 내 집수를 add
			}
		}
		Collections.sort(count); // 단지 내 집수를 오름차순으로 sort
		System.out.println(count.size()); // 총 단지 수 출력
		for(int i=0; i<count.size(); i++) {
			System.out.println(count.get(i)); // 단지 내 집수 출력
		}

	}
	
	static void dfs(int x, int y) {
		cnt++;
		visited[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0||ny<0||nx>=n||ny>=n) continue;
			if(visited[nx][ny])	continue;
			if(arr[nx][ny]=='0') continue;
			
			dfs(nx, ny);
		}
		
	}
}
