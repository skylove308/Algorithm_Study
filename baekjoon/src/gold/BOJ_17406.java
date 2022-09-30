package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17406 {
	static int[][] rotate_info, result, arr, arr2, temp;
	static boolean[] used;
	static int n, m, k, ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로축 크기
		m = Integer.parseInt(st.nextToken()); // 가로축 크기
		k = Integer.parseInt(st.nextToken()); // 회전 연산 수
		arr = new int[n+1][m+1]; // 배열
		arr2 = new int[n+1][m+1]; // 배열
		temp = new int[n+1][m+1]; // 임시 저장 배열
		rotate_info = new int[k][3]; // 회전 정보
		result = new int[k][3];
		used = new boolean[k]; // 순열시 사용되는 기억 배열
		
		for(int i=1; i<=n; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++) {
				arr[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
		
		for(int i=0; i<k; i++) {
			StringTokenizer st3 = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				rotate_info[i][j] = Integer.parseInt(st3.nextToken());
			}
		}
		perm(0);
		System.out.println(ans);
	}
	
	static void perm(int idx) {
		if(idx == k) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=m; j++) {
					arr2[i][j] = arr[i][j];
				}
			}
			for(int i=0; i<k; i++) {
				rotate(result[i][0], result[i][1], result[i][2]);
			}
			int tmp = calculate();
			ans = ans < tmp ? ans : tmp; // 제일 낮은 것
		}
		for(int i=0; i<k; i++) {
			if(used[i]) continue;
			result[idx] = rotate_info[i];
			used[i] = true;
			perm(idx+1);
			used[i] = false;
		}
	}
	
	// 배열 회전
	static void rotate(int r, int c, int s) {
		temp = new int[n+1][m+1]; // 회전 하는 부분만 저장
		for(int t=0; t<s; t++) {
			for(int j=c-s+t; j<c+s-t; j++) {
				int i = r-s+t;
				temp[i][j+1] = arr2[i][j];
			}
			for(int i=r-s+t; i<r+s-t; i++) {
				int j = c+s-t;
				temp[i+1][j] = arr2[i][j];
			}
			for(int j=c+s-t; j>c-s+t; j--) {
				int i = r+s-t;
				temp[i][j-1] = arr2[i][j];
			}
			for(int i=r+s-t; i>r-s+t; i--) {
				int j = c-s+t;
				temp[i-1][j] = arr2[i][j];
			}
		}
		temp[r][c] = arr2[r][c]; // 맨 가운데 축은 회전 안함
		// 다시 원래 배열에 저장
		for(int i=r-s; i<=r+s; i++) {
			for(int j=c-s; j<=c+s; j++) {
				arr2[i][j] = temp[i][j];
			}
		}
	}
	
	// 배열 점수 계산
	static int calculate() {
		int min = Integer.MAX_VALUE;
		for(int i=1; i<=n; i++) {
			int sum = 0;
			for(int j=1; j<=m; j++) {
				sum += arr2[i][j];
			}
			min = min < sum ? min : sum;
		}
		return min;
	}
}
