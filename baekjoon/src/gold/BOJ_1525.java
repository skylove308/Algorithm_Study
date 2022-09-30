package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x, y, count;
	String puzzle;
	public Point(int x, int y, int count, String puzzle) {
		this.x = x;
		this.y = y;
		this.count = count;
		this.puzzle = puzzle;
	}
}

public class BOJ_1525 {
	static int min = Integer.MAX_VALUE;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static HashSet<String> puzzles; // set의 add와 contains의 시간 복잡도는 O(1), 지난 퍼즐의 모양을 담을 set
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String puzzle = ""; // 퍼즐을 String에 담는다.
		int x = 0;
		int y = 0;
		for(int i=0; i<3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				String s = st.nextToken();
				puzzle += s;
				if(s.equals("0")) { // 0의 index 찾기
					x = i;
					y = j;
				}
			}
		}
		puzzles = new HashSet<>(); 
		puzzles.add(puzzle);
		bfs(new Point(x, y, 0, puzzle));
		if(min==Integer.MAX_VALUE) 
			System.out.println(-1);
		else
			System.out.println(min);
		
	}
	
	static void bfs(Point zeroPoint) {
		Queue<Point> q = new LinkedList<>();
		q.add(zeroPoint);
		while(!q.isEmpty()) {
			Point pos = q.poll();
			if(pos.puzzle.equals("123456780")) {
				min = pos.count;
				break;
			}
			for(int i=0; i<4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				
				if(nx<0||ny<0||nx>=3||ny>=3) continue;
				
				// (nx, ny)의 인덱스와 (x, y)의 인덱스의 숫자 교환
				char c = pos.puzzle.charAt(3*nx+ny);
				String s = pos.puzzle.replace(c, 'A');
				s = s.replace('0', c);
				s = s.replace('A', '0');
				
				// 이미 같은 퍼즐 모양이 있었다면 pass
				if(puzzles.contains(s)) continue;
				
				puzzles.add(s);
				q.add(new Point(nx, ny, pos.count+1, s));
			}
		}
	}
	
}
