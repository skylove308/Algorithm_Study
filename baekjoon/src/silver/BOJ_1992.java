package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1992 {
	static char[][] arr;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		// 문제 입력 끝
		sb = new StringBuilder(); // 출력 담는 변수 초기화
		quadTree(0, 0, n, n); // 메인 메소드
		System.out.println(sb); // 출력
	}
	
	static void quadTree(int x1, int y1, int x2, int y2) {
		int count = 0;
		for(int i=x1; i<x2; i++) {
			for(int j=y1; j<y2; j++) {
				if(arr[i][j] == '1') 
					count++;
			}
		}
		if(count==0) sb.append(0); // 모두 0이면
		else if(count==(x2-x1)*(x2-x1)) sb.append(1); // 모두 1이면
		else {
			sb.append("(");
			quadTree(x1, y1, (x1+x2)/2, (y1+y2)/2);
			quadTree(x1, (y1+y2)/2, (x1+x2)/2, y2);
			quadTree((x1+x2)/2, y1, x2, (y1+y2)/2);
			quadTree((x1+x2)/2, (y1+y2)/2, x2, y2);
			sb.append(")");
		}
	}
}
