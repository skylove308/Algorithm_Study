package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3040 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[9];
		int sum = 0;
		for(int i=0; i<9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		
		int a = 0;
		int b = 0;
		for(int i=0; i<9; i++) {
			for(int j=i+1; j<9; j++) {
				if((sum - (arr[i]+arr[j])) == 100) {
					a = i;
					b = j;
				}
			}
		}
		for(int i=0; i<9; i++) {
			if(i==a||i==b) continue;
			System.out.println(arr[i]);
		}
	}
}
