package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_1208 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int test_case=0; test_case<10; test_case++) {
			int dump = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			int[] boxes = new int[100];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<100; i++) {
				boxes[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(boxes);
			for(int i=0; i<dump; i++) {
				boxes[99]-=1;
				boxes[0]+=1;
				Arrays.sort(boxes);
			}
			System.out.println("#"+(test_case+1)+" "+(boxes[99]-boxes[0]));
		}
		
	}
}
