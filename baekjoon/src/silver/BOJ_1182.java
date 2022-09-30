package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182 {
	static int n, s, ans;
	static int[] arr;
	static boolean[] selected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st2.nextToken());
		}
		selected = new boolean[n];
		powerSet(0);
		System.out.println(ans);
	}
	
	static void powerSet(int idx) {
		if(idx==n) {
			int sum = 0;
			boolean check = false;
			for(int i=0; i<n; i++) {
				if(selected[i]) {
					check = true;
					sum += arr[i];	
				}	
			}
			if(sum==s&&check==true)
				ans++;
			return;
		}
		selected[idx] = true;
		powerSet(idx+1);
		selected[idx] = false;
		powerSet(idx+1);
	}
}
