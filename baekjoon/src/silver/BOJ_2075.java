package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2075 {
	static class Point {
		int x, y, num;
		public Point(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Point[][] arr = new Point[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = new Point(i, j, Integer.parseInt(st.nextToken()));
			}
		}
		
		PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> {
			return o2.num - o1.num;
		});
		
		for(int j=0; j<N; j++) {
			pq.add(arr[N-1][j]);
		}
		
		int cnt = N;
		Point np = new Point(0, 0, 0);
		while(cnt > 0) {
			np = pq.poll();
			cnt--;
			if(np.x==0) 
				continue;
			pq.add(arr[np.x-1][np.y]);
		}
		
		System.out.println(np.num);
		
	}
}
