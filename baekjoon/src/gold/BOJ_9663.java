package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663 {
	static int n, ans;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		nQueen(0);
		System.out.println(ans);
	}
	
	static void nQueen(int idx) {
		if(idx == n) {
			ans++;
		}
		for(int i=0; i<n; i++) {
			boolean check = true;
			for(int j=0; j<idx; j++) {
				if(i==arr[j] || Math.abs(i-arr[j]) == Math.abs(idx-j)) {
					check = false;
					break;
				}	
			}
			if(check) {
				arr[idx] = i;
				nQueen(idx+1);
			}
			
		}	
	}
}
