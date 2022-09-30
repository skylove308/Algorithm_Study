package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17281 {
	static int n, max;
	static int[][] hit;
	static int[] hitters;
	static boolean[] used;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		hit = new int[n][9];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				hit[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		hitters = new int[9];
		used = new boolean[9];
		max = 0;
		// 문제 입력 끝
		
		perm(0);

		System.out.println(max);
	}
	
	static void perm(int idx) {
		if(idx==3) {
			hitters[idx] = 0;
			perm(idx+1);
		}
		if(idx==9) {
			int score = 0;
			int first_hitter = 0;
			for(int i=0; i<n; i++) {
				int out = 0;
				int j = first_hitter;
				int[] base = new int[4]; // 베이스에 저장된 숫자가 몇번째 베이스에 사람이 있다는 뜻
				while(true) {
					j %= 9; // 10번째 타자는 다시 1번으로
					if(hit[i][hitters[j]]>0) { // 아웃이 아니라면
						if(hit[i][hitters[j]]==4) { // 홈런 이면
							for(int k=0; k<4; k++) { 
								if(base[k]!=0) { // 주자가 있으면 모두 점수가 됨
									base[k]=0; // 홈런이면 베이스를 깨끗하게
									score++;
								}
							}
							score++;
						}
						else {
							boolean check = false; // 안타쳤을 때 주자 한번만 base에 넣어주기 위한 체크 변수
							for(int k=0; k<4; k++) {
								if(base[k]==0 && !check) { // 베이스에 주자가 없으면 한번만 채움
									base[k] = hit[i][hitters[j]];
									check = true;
								}
								else if(base[k]>0){ // 베이스에 주자가 있으면
									if(base[k]+hit[i][hitters[j]]>=4) { // 홈으로 들어오면 점수가 남
										base[k] = 0; // 홈에 들어왔으면 베이스를 치우기
										score++;
									}
									else {
										base[k] += hit[i][hitters[j]]; // 주자가 친 타구만큼 베이스 진루
									}
								}
							}
						}
						j++;
					}
					else { // 아웃이라면
						out++;
						if(out==3) {
							first_hitter = (j+1)%9;
							break; // 아웃카운트가 3개가 될때 이닝 종료
						}
						j++;
					}
				}
			}
			max = Math.max(max, score); // 최대점수 구하기
			return;
		}
		
		for(int i=1; i<9; i++) {
			if(used[i]) continue;
			hitters[idx] = i;
			used[i] = true;
			perm(idx+1);
			used[i] = false;
		}
	}
}
