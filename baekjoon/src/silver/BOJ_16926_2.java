package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16926_2 {
	static String[][] arr, ans;
	static int n, m, r;
	public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new String[n+1][m+1];
		for(int i=1; i<=n; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++) {
				arr[i][j] =  st2.nextToken();
			}
		}
		ans = new String[n+1][m+1];
		
		int x = n;
		int y = m;
		while(x>=1 && y>=1) {
			LinkedList<String> l = new LinkedList<>();
			Queue<String> l2 = new LinkedList<>();
			for(int j=1+(m-y)/2; j<(m+y)/2; j++) {
				int i=1+(n-x)/2;
				l.add(arr[i][j]);
			}
			for(int i=1+(n-x)/2; i<(n+x)/2; i++) {
				int j=(m+y)/2;
				l.add(arr[i][j]);
			}
			for(int j=(m+y)/2; j>1+(m-y)/2; j--) {
				int i=(n+x)/2;
				l.add(arr[i][j]);
			}
			for(int i=(n+x)/2; i>1+(n-x)/2; i--) {
				int j=1+(m-y)/2;
				l.add(arr[i][j]);
			}

			int index = r%(2*x+2*y-4);
			for(int i=index; i<2*x+2*y-4+index; i++) {
				l2.add(l.get(i%(2*x+2*y-4)));
			}
			
			for(int j=1+(m-y)/2; j<(m+y)/2; j++) {
				int i=1+(n-x)/2;
				ans[i][j] = l2.poll();
			}
			for(int i=1+(n-x)/2; i<(n+x)/2; i++) {
				int j=(m+y)/2;
				ans[i][j] = l2.poll();
			}
			for(int j=(m+y)/2; j>1+(m-y)/2; j--) {
				int i=(n+x)/2;
				ans[i][j] = l2.poll();
			}
			for(int i=(n+x)/2; i>1+(n-x)/2; i--) {
				int j=1+(m-y)/2;
				ans[i][j] = l2.poll();
			}
			x -= 2;
			y -= 2;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				sb.append(ans[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
	
	

