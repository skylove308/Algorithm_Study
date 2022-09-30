package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144 {
	static int[][] room;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int acPosX;
	static int r, c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		room = new int[r][c];
		for(int i=0; i<r; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0; j<c; j++) {
				room[i][j] = Integer.parseInt(st2.nextToken());
				if(room[i][j]==-1) 
					acPosX = i;
			}
		}
		
		for(int sc=0; sc<t; sc++) {
			spread();
			clean();
		}
		int ans = 0;
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(room[i][j]>0)
					ans += room[i][j];
			}
		}
		System.out.println(ans);
		
	}
	
	static void spread() {
		int nx, ny;
		int[][] temp = new int[r][c];
		for(int x=0; x<r; x++) {
			for(int y=0; y<c; y++) {
				int cnt = 0;
				if(room[x][y]>0) {
					temp[x][y] += room[x][y];
					for(int i=0; i<4; i++) {
						nx = x + dx[i];
						ny = y + dy[i];
						if(nx<0||ny<0||nx>=r||ny>=c||room[nx][ny]==-1) {
							cnt++;
							continue;
						}
					}
					for(int i=0; i<4; i++) {
						nx = x + dx[i];
						ny = y + dy[i];
						if(nx<0||ny<0||nx>=r||ny>=c||room[nx][ny]==-1) 
							continue;
						temp[nx][ny] += room[x][y]/5;
					}
					temp[x][y] -= room[x][y]/5*(4-cnt);
				}
			}
		}
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				room[i][j] = temp[i][j];
			}
		}
		
	}
	
	static void clean() {
		int[][] temp = new int[r][c];
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				temp[i][j] = room[i][j];
			}
		}
		
		for(int i=0; i<c-1; i++) {
			temp[0][i] = room[0][i+1];
		}
		for(int i=0; i<acPosX-1; i++) {
			temp[i][c-1] = room[i+1][c-1];
		}
		for(int i=0; i<c-1; i++) {
			temp[acPosX-1][i+1] = room[acPosX-1][i];
		}
		for(int i=0; i<acPosX-1; i++) {
			temp[i+1][0] = room[i][0];
		}
		
		for(int i=0; i<c-1; i++) {
			temp[acPosX][i+1] = room[acPosX][i];
		}
		for(int i=acPosX; i<r-1; i++) {
			temp[i+1][c-1] = room[i][c-1];
		}
		for(int i=0; i<c-1; i++) {
			temp[r-1][i] = room[r-1][i+1];
		}
		for(int i=acPosX; i<r-1; i++) {
			temp[i][0] = room[i+1][0];
		}
		
		temp[acPosX-1][0] = -1;
		temp[acPosX][0] = -1;
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				room[i][j] = temp[i][j];
			}
		}

	}

}
