package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961 {
	static int n, min;
	static int[][] ingredient;
	static boolean[] select;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ingredient = new int[n][2];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ingredient[i][0] = Integer.parseInt(st.nextToken());
			ingredient[i][1] = Integer.parseInt(st.nextToken());
		}
		select = new boolean[n];
		min = Integer.MAX_VALUE; // min 초기화
		// 문제 입력 끝
		powerset(0);
		System.out.println(min);
	}
	
	static void powerset(int idx) { // 부분집합 사용
		if(idx==n) {
			int s = 1; // 신맛 (신맛은 곱)
			int b = 0; // 쓴맛 (쓴맛은 합)
			boolean check = false; // 재료가 한 개라도 사용 되었는지 체크
			for(int i=0; i<n; i++) {
				if(select[i]) {
					s *= ingredient[i][0];
					b += ingredient[i][1];
					check = true;
				}
			}
			if(check) {
				min = min < Math.abs(s - b) ? min : Math.abs(s - b); // 모든 부분집합에 대하여 최소값 min 도출
			}
			return;
		}
		select[idx] = true;
		powerset(idx+1);
		select[idx] = false;
		powerset(idx+1);
	}
}
