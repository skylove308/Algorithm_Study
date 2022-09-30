package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926 {
	static String[][] arr, ans;
	static int n, m, r;
	public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new String[n+1][m+1];
		ans = new String[n+1][m+1];
		for(int i=1; i<=n; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++) {
				arr[i][j] =  st2.nextToken();
			}
		}
		
		int n1 = n;
		int m1 = m;
		while(n1>=1 && m1>=1) {
			rotate(n1, m1, r);
			n1-=2;
			m1-=2;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				sb.append(ans[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	
	static void rotate(int x, int y, int r) {
		for(int i=1; i<x; i++) {
			int j=1;
			divide_rotate(i, j, x, y, r);
		}
		for(int j=1; j<y; j++) {
			int i=x;
			divide_rotate(i, j, x, y, r);
		}
		for(int i=x; i>1; i--) {
			int j=y;
			divide_rotate(i, j, x, y, r);
		}
		for(int j=y; j>1; j--) {
			int i=1;
			divide_rotate(i, j, x, y, r);
		}
	}
	
	static void divide_rotate(int i, int j, int x, int y, int r) {
		String temp = arr[i+(n-x)/2][j+(m-y)/2];
		while (r>0) {
			if(i<x && j==1) {
				if(i+r>=x) {
					j = 1;
					r -= x - i;
					i = x;
				}
				else {
					i += r;
					j = 1;
					r = 0;
				}
			}
			else if(i==x && j<y) {
				if(j+r>=y) {
					i = x;
					r -= y - j;
					j = y;
				}
				else {
					i = x;
					j += r;
					r = 0;
				}
			}
			else if(i>1 && j==y) {
				if(i-r<=1) {
					j = y;
					r -= i - 1;
					i = 1;
				}
				else {
					i -= r;
					j = y;
					r = 0;
				}
			}
			else if(i==1 && j>1) {
				if(j-r<=1) {
					i = 1;
					r -= j - 1;
					j = 1;
				}
				else {
					i = 1;
					j -= r;
					r = 0;
				}
			}
		}
		ans[i+(n-x)/2][j+(m-y)/2] = temp;
	}
	
}
