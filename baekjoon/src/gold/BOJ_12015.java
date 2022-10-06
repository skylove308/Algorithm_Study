package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12015 {
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
		int len = 0;
		for(int i=0; i<N; i++) {
			if(A[i] > dp[len]) {
				dp[++len] = A[i];
			}else {
				int key = binarySearch(1, len, A[i]);
				dp[key] = A[i];
			}
		}
		System.out.println(len);
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
