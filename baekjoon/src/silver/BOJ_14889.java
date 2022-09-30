package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889 {
	static int n, min;
	static int[][] ability;
	static int[] number;
	static int[] team1, team2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ability = new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		number = new int[n]; // 등번호 (1~n)
		for(int i=0; i<n; i++) {
			number[i] = i+1;
		}
		team1 = new int[n/2]; // team 스타트
		team2 = new int[n/2]; // team 링크
		
		min = Integer.MAX_VALUE; // min 초기화
		// 문제 입력 끝
		comb(0, 0);
		System.out.println(min);
		
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == n/2) {
			int k = 0;
			for(int i=1; i<=n; i++) { // team1에 없는 숫자들이 team2에 있다.
				boolean check = false;
				for(int j=0; j<n/2; j++) {
					if(team1[j]==i) check = true;
				}
				if(!check) team2[k++] = i;
			}
			
			int sum1 = 0;
			int sum2 = 0;
			for(int i=0; i<n/2; i++) { // team1과 team2의 모든 능력치를 합산한다.
				for(int j=i+1; j<n/2; j++) {
					sum1 += ability[team1[i]][team1[j]] + ability[team1[j]][team1[i]];
					sum2 += ability[team2[i]][team2[j]] + ability[team2[j]][team2[i]];
				}
			}
			min = min < Math.abs(sum1 - sum2) ? min : Math.abs(sum1 - sum2); // 모든 조합에 대하여 능력치 비교하여 최솟값 도출
			return;
		}
		for(int i=idx; i<n; i++) {
			team1[cnt] = number[i];
			comb(i+1, cnt+1);
		}
		
		
	}
}
