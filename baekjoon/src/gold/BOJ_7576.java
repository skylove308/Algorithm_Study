package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
	int i, j, day;
	public Tomato(int i, int j, int day){
		this.i = i;
		this.j = j;
		this.day = day;
	}
}

public class BOJ_7576 {
	static int n, m;
	static int[][] box;
	static boolean[][] visited;
	static Queue<Tomato> tomato;
	static int countNotRiped;
	static Tomato t;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		box = new int[n][m];
		tomato = new LinkedList<>();
		visited = new boolean[n][m];
		for(int i=0; i<n; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				box[i][j] = Integer.parseInt(st2.nextToken());
				if(box[i][j]==1)
					tomato.add(new Tomato(i, j, 0)); // 익은 토마토 저장
				else if(box[i][j]==0) 
					countNotRiped++; // 안익은 토마토 갯수 세기
			}
		}
		// 문제 입력 끝
		
		System.out.println(bfs());
		
	}
	
	static int bfs() {
		if(countNotRiped==0) return 0; // 처음 시작때 모든 토마토가 익어있으면 0
		while(countNotRiped>0 && !tomato.isEmpty()) {
			t = tomato.poll();
			int px = t.i;
			int py = t.j;
			int day = t.day;
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				if(nx<0||ny<0||nx>=n||ny>=m) continue; // 배열 범위 밖이면 pass
				if(box[nx][ny]==1 || box[nx][ny]==-1) continue; // 박스에 토마토가 익었으면 pass
				tomato.add(new Tomato(nx, ny, day+1)); // 큐에 새로운 토마토 삽입
				box[nx][ny] = 1; // 토마토가 익음
				countNotRiped--; // 안익은 토마토 1개 제거
			}
		}
		if(countNotRiped>0) return -1; // 방문할 수 있는 모든 box를 방문했는데도 안익은 토마토가 남아있으면 -1
		return t.day+1; // 안익은 토마토가 사라지면 countNotRiped가 0이되어 반복문 종료. 따라서 큐에서 나온 마지막 토마토의 day에 1을 더해주어야한다.
	}
}
