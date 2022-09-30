package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074 {
	static int n, r, c;
	static int[] count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		count = new int[n];
		int ans = 0;
		if(n==0) {
			ans = 0;
		}
		else if(n==1) {
			if(r==0&&c==0) ans = 0;
			else if(r==0&&c==1) ans = 1;
			else if(r==1&&c==0) ans = 2;
			else ans = 3;
		}
		else {
			visited_count(n);
			for(int i=1; i<n; i++) {
				ans += count[i] * Math.pow(2, i) * Math.pow(2, i);
	 		}
			if(r==0&&c==0) ans += 0;
			else if(r==0&&c==1) ans += 1;
			else if(r==1&&c==0) ans += 2;
			else ans += 3;
		}

		System.out.println(ans);
	}
	
	static void visited_count(int num) {
		if(0<=r && r<Math.pow(2, num-1) && 0<=c && c<Math.pow(2, num-1)){
			count[num-1] = 0;
		}
		else if(0<=r && r<Math.pow(2, num-1) && Math.pow(2, num-1)<=c && c<Math.pow(2, num)){
			count[num-1] = 1;
			c -= Math.pow(2, num-1);
		}
		else if(Math.pow(2, num-1)<=r && r<Math.pow(2, num) && 0<=c && c<Math.pow(2, num-1)){
			count[num-1] = 2;
			r -= Math.pow(2, num-1);
		}
		else if(Math.pow(2, num-1)<=r && r<Math.pow(2, num) && Math.pow(2, num-1)<=c && c<Math.pow(2, num)){
			count[num-1] = 3;
			r -= Math.pow(2, num-1);
			c -= Math.pow(2, num-1);
		}
		
		if(num==2) return;
		visited_count(num-1);
	}
}
