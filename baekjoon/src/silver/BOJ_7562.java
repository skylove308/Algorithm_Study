package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class BOJ_7562 {
	static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int l, start_i, start_j, end_i, end_j;
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=t; test_case++) {
			l = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			start_i = Integer.parseInt(st.nextToken());
			start_j = Integer.parseInt(st.nextToken());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			end_i = Integer.parseInt(st2.nextToken());
			end_j = Integer.parseInt(st2.nextToken());
			visited = new boolean[l][l];
			System.out.println(bfs());
		}
		
	}
	static int bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(start_i, start_j));
		visited[start_i][start_j] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int n=0; n<size; n++) {
				Point pos = q.poll();
				if(pos.x == end_i && pos.y == end_j) return cnt;
				for(int i=0; i<8; i++) {
					int nx = pos.x + dx[i];
					int ny = pos.y + dy[i];
					if(nx<0||ny<0||nx>=l||ny>=l) continue;
					if(visited[nx][ny]) continue;
					visited[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
			cnt++; // 같은 depth에서 도는 친구들은 무시하고 depth가 바뀔때마다 +1해준다.
		}
		return -1;
	}
}
