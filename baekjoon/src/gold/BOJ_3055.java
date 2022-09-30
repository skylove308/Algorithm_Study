package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_3055 {
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Point> water;
	static Queue<Point> dochi;
	static char[][] map;
	static int r, c;
	static boolean[][][] visited;
	static Point destination;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		water = new LinkedList<>();
		dochi = new LinkedList<>();
		visited = new boolean[r][c][2];
		for(int i=0; i<r; i++) {
			String s = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='S') {
					dochi.add(new Point(i, j));
					visited[i][j][0] = true;
				}
				else if(map[i][j]=='*') {
					water.add(new Point(i, j));
					visited[i][j][1] = true;
				}
				else if(map[i][j]=='D') {
					destination = new Point(i, j);
				}
			}
		}
		
		int ans = bfs();
		if(ans == -1) {
			System.out.println("KAKTUS");
		}
		else {
			System.out.println(ans);
		}
		
	}
	
	static int bfs() {
		int cnt = 0;
		while(!dochi.isEmpty()) {
			int size = water.size();
			for(int s=0; s<size; s++) {
				Point w = water.poll();
				for(int i=0; i<4; i++) {
					int nx=w.x+dx[i];
					int ny=w.y+dy[i];
					if(nx<0||ny<0||nx>=r||ny>=c) continue;
					if(visited[nx][ny][1]) continue;
					if(map[nx][ny]=='X'||map[nx][ny]=='D') continue;
					water.add(new Point(nx, ny));
					map[nx][ny] = '*';
					visited[nx][ny][1] = true;
				}
			}
			size = dochi.size();
			for(int s=0; s<size; s++) {
				Point d = dochi.poll();
				if(d.x==destination.x&&d.y==destination.y)
					return cnt;
				for(int i=0; i<4; i++) {
					int nx=d.x+dx[i];
					int ny=d.y+dy[i];
					if(nx<0||ny<0||nx>=r||ny>=c) continue;
					if(visited[nx][ny][0]) continue;
					if(map[nx][ny]=='X'||map[nx][ny]=='*') continue;
					dochi.add(new Point(nx, ny));
					visited[nx][ny][0] = true;
				}
			}
			cnt++;
		}
		return -1;
	
	}
}
