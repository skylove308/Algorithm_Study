package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1174 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[11][10];
		for(int i=0; i<10; i++) {
			arr[1][i] = 1;
		}
		for(int i=2; i<=10; i++) { // i 자리 숫자
			for(int j=1; j<10; j++) { // 맨 앞자리 숫자가 j
				for(int k=0; k<j; k++) {
					// i자리 숫자의 맨 앞자리 숫자가 j인 숫자들의 줄어드는 수 갯수는
					// i-1자리숫자들의 맨 앞자리가 0부터 j-1까지인 줄어드는 수의 총합과 같다.
					arr[i][j] += arr[i-1][k]; 
				}
			}
		}
		
		if(n==1) 
			System.out.println(0); // n이 1이면 0 출력
		else {
			n--; // 첫번째 작은 줄어드는 수 0은 지나갔으므로 n에서 1감소
			int x = 0, y = 0; // i,j를 저장할 변수
			loop:
			for(int i=1; i<=10; i++) {
				for(int j=1; j<10; j++) {
					n -= arr[i][j]; // n번째 줄어드는 수 위치 찾기
					if(n<=0) { // arr[i][j]에 있다
						x = i;
						y = j;
						break loop;
					}
				}
			}
			if(n>0) // 줄어드는 수를 찾지 못했으면
				System.out.println(-1);
			else {
				StringBuilder sb = new StringBuilder();
				sb.append(y);
				n += arr[x][y]; // 다시 n에 더해줌
				// 줄어드는 수의 정확한 위치를 찾기 위해 다음과같은 반복문 실행 
				for(int i=1; i<x; i++) {
					for(int j=0; j<y; j++) {
						n-=arr[x-i][j];
						if(n<=0){
							sb.append(j);
							n += arr[x-i][j];
							break;
						}
					}
				}
				System.out.println(sb);
			}	
		}	
	}
}
