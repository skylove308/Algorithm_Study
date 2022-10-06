package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14002 {
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N+1];
		for(int i=0; i<=N; i++) {
			dp[i] = Integer.MIN_VALUE;
		}
		int[] lis = new int[N+1];
		int len = 0;
		for(int i=0; i<N; i++) {
			if(A[i] > dp[len]) {
				dp[++len] = A[i];
				lis[i] = len;
			}else {
				int key = binarySearch(1, len, A[i]);
				dp[key] = A[i];
				lis[i] = key;
			}
		}

		int tmp_len = len;
		int[] ans = new int[N+1];
		for(int i=N-1; i>=0; i--) {
			if(tmp_len == lis[i]) {
				ans[tmp_len--] = A[i];
			}
		}
		
		System.out.println(len);
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=len; i++) {
			sb.append(ans[i]+" ");
		}
		System.out.println(sb);
	}
	
	static int binarySearch(int left, int right, int key) {
		int mid;
		while(left < right) {
			mid = (left + right) / 2;
			if(dp[mid] < key) {
				left = mid + 1;
			}else {
				right = mid;
			}
		}
		return right;
	}
}
