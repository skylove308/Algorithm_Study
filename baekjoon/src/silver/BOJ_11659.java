package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n+1];
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		arr[0] = 0;
		for(int i=1; i<n+1; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st2.nextToken());		
		}
		
		for(int i=0; i<m; i++) {
			StringTokenizer st3 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st3.nextToken());
			int b = Integer.parseInt(st3.nextToken());
			System.out.println(arr[b] - arr[a-1]);
		}
	}
}
