package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SW_1228 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int test_case=1; test_case<=10; test_case++) {
			int n = Integer.parseInt(br.readLine().trim());
			LinkedList<String> password = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<10; i++) { // 암호문 중 10개만 배열에 넣기
				password.add(st.nextToken());
			}
			int m = Integer.parseInt(br.readLine().trim());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) {
				st2.nextToken();
				int index = Integer.parseInt(st2.nextToken());
				int t = Integer.parseInt(st2.nextToken());
				for(int j=0; j<t; j++) {
					if(index<=9) {
						password.add(index++, st2.nextToken());
					}
					else {
						st2.nextToken();
					}
				}
					
			}
			String[] ans = new String[10];
			for(int i=0; i<10; i++) {
				ans[i] = password.get(i);
			}
			StringBuilder sb = new StringBuilder();
			for(String s: ans) {
				sb.append(s+" ");
			}
			System.out.println("#"+test_case+" "+sb);
		}
	}
}
