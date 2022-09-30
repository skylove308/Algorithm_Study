package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// cctv 클래스
class CCTV {
	int i;
	int j;
	int k;
	public CCTV(int i, int j, int k) {
		this.i = i;
		this.j = j;
		this.k = k;
	}
}

public class BOJ_15683 {
	static List<CCTV> cctv;
	static int[][] room, room_temp;
	static int n, m;
	static int[] cctv_direction;
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로 크기
		m = Integer.parseInt(st.nextToken()); // 가로 크기
		room = new int[n][m]; // 전체 방 배열
		room_temp = new int[n][m]; // 임시 방 배열
		cctv = new ArrayList<>(); // cctv 배열
		
		// 전체 room 좌표 입력
		// cctv 체크해서 리스트에 넣어줌
		for(int i=0; i<n; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				room[i][j] = Integer.parseInt(st2.nextToken());
				if(1<=room[i][j] && room[i][j]<=5) {
					cctv.add(new CCTV(i, j, room[i][j]));
				}
			}
		}
		
		cctv_direction = new int[cctv.size()]; // cctv방향에 따른 배열
		min = Integer.MAX_VALUE; // 최솟값 초기화
		watch_rotate(0); // 메인 메소드 실행
		System.out.println(min); // 정답 출력
	}
	
	static void watch_rotate(int cnt) {
		if(cnt==cctv.size()) { // 재귀 종료 조건
			
			// room_temp 초기화
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					room_temp[i][j] = room[i][j];
				}
			}
			
			for(int i=0; i<cnt; i++) {
				// cctv 현재 좌표
				int x = cctv.get(i).i;
				int y = cctv.get(i).j;
				
				// cctv종류에 따라 좌표 i, j에서 감시 카메라를 비추어 room_temp맵에 표시한다. 
				switch(cctv.get(i).k) {
				case 1:
					if(cctv_direction[i]==0) 
						rotate(x, y, 0);			
					else if(cctv_direction[i]==1) 
						rotate(x, y, 90);
					else if(cctv_direction[i]==2) 
						rotate(x, y, 180);
					else if(cctv_direction[i]==3) 
						rotate(x, y, 270);
					break;
				case 2:
					if(cctv_direction[i]==0||cctv_direction[i]==2) {
						rotate(x, y, 0);
						rotate(x, y, 180);
					}
					else if(cctv_direction[i]==1||cctv_direction[i]==3) {
						rotate(x, y, 90);
						rotate(x, y, 270);
					}
					break;
				case 3:
					if(cctv_direction[i]==0) {
						rotate(x, y, 0);
						rotate(x, y, 90);
					}
					else if(cctv_direction[i]==1) {
						rotate(x, y, 90);
						rotate(x, y, 180);
					}
					else if(cctv_direction[i]==2) {
						rotate(x, y, 180);
						rotate(x, y, 270);
					}
					else if(cctv_direction[i]==3) {
						rotate(x, y, 270);
						rotate(x, y, 0);
					}
					break;
				case 4:
					if(cctv_direction[i]==0) {
						rotate(x, y, 0);
						rotate(x, y, 90);
						rotate(x, y, 180);
					}
					else if(cctv_direction[i]==1) {
						rotate(x, y, 90);
						rotate(x, y, 180);
						rotate(x, y, 270);
					}
					else if(cctv_direction[i]==2) {
						rotate(x, y, 180);
						rotate(x, y, 270);
						rotate(x, y, 0);
					}
					else if(cctv_direction[i]==3) {
						rotate(x, y, 270);
						rotate(x, y, 0);
						rotate(x, y, 90);
					}
					break;
				case 5:
					rotate(x, y, 0);
					rotate(x, y, 90);
					rotate(x, y, 180);
					rotate(x, y, 270);
					break;
				}
			}
			
			int count = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(room_temp[i][j]==0) count++; // 카메라가 비추지 못하는 부분(0) 카운트
				}
			}
			min = Math.min(min, count); // 전체에서 최솟값 
			return;
			
		}
		// 0~3으로 이루어진 중복순열 생성
		for(int i=0; i<4; i++) {
			cctv_direction[cnt]=i;
			watch_rotate(cnt+1);		
		}
		
	}
	
	// z만큼 회전했을때 감시카메라가 비추는 영역 표시 (초기값 -> 방향의 카메라)
	// 카메라가 비추는 부분은 -1로 바꿈
	static void rotate(int x, int y, int z) {
		switch(z) {
		case 0:
			for(int j=y+1; j<m; j++) {
				if(room_temp[x][j]==6) break; // 벽을 만나면 더이상 진행하지 않고 종료
				if(1<=room_temp[x][j]&&room_temp[x][j]<=5) continue; // 카메라를 만나면 계속 진행
				room_temp[x][j] = -1; // 카메라가 비추는 부분은 -1로 표시
			}
			break;
		case 90:
			for(int i=x-1; i>=0; i--) {
				if(room_temp[i][y]==6) break;
				if(1<=room_temp[i][y]&&room_temp[i][y]<=5) continue;
				room_temp[i][y] = -1;
			}
			break;
		case 180:
			for(int j=y-1; j>=0; j--) {
				if(room_temp[x][j]==6) break;
				if(1<=room_temp[x][j]&&room_temp[x][j]<=5) continue;
				room_temp[x][j] = -1;
			}
			break;
		case 270:
			for(int i=x+1; i<n; i++) {
				if(room_temp[i][y]==6) break;
				if(1<=room_temp[i][y]&&room_temp[i][y]<=5) continue;
				room_temp[i][y] = -1;
			}
			break;
		}
	}
}
