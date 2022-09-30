package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1873 {
	static int x, y, H, W;
	static char dir;
	static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W]; // 맵 정보
			for(int i=0; i<H; i++) {
				String s = new StringTokenizer(br.readLine()).nextToken();
				for(int j=0; j<W; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j] == '^') {
						x = i;
						y = j;
						dir = 'U';
					}
					else if(map[i][j] == 'v') {
						x = i;
						y = j;
						dir = 'D';
					}
					else if(map[i][j] == '<') {
						x = i;
						y = j;
						dir = 'L';
					}
					else if(map[i][j] == '>') {
						x = i;
						y = j;
						dir = 'R';
					}
					
				}
			}
			map[x][y] = '.';

			int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			char[] command = new char[N]; // 사용자 커맨드
			String s = new StringTokenizer(br.readLine()).nextToken();
			for(int i=0; i<N; i++) {
				command[i] = s.charAt(i);
			}
			
			for(int i=0; i<N; i++) {
				if(command[i] == 'S') {
					shoot();
				}
				else {
					move(command[i]);
				}
			}
			
			if(dir == 'U') {
				map[x][y] = '^';
			}
			else if(dir == 'D') {
				map[x][y] = 'v';
			}
			else if(dir == 'L') {
				map[x][y] = '<';
			}
			else if(dir == 'R') {
				map[x][y] = '>';
			}
			System.out.print("#"+test_case+" ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
		}

		
	}
	
	static void move(char c) {
		if(c == 'U') {
			dir = 'U';
			if(x-1>=0 && map[x-1][y] == '.') {
				x--;
			}
		}
		else if(c == 'D') {
			dir = 'D';
			if(x+1<H && map[x+1][y] == '.') {
				x++;
			}
		}
		else if(c == 'L') {
			dir = 'L';
			if(y-1>=0 && map[x][y-1] == '.') {
				y--;
			}
		}
		else if(c == 'R') {
			dir = 'R';
			if(y+1<W && map[x][y+1] == '.') {
				y++;
			}
		}
	}
	
	static void shoot() {
		int i = x;
		int j = y;
		if(dir == 'U') {
			while(i>=1) {
				i--;
				if(map[i][j] == '*') {
					map[i][j] = '.';
					break;
				}
				else if(map[i][j] == '#') {
					break;
				}
			}
		}
		else if(dir == 'D') {
			while(i<H-1) {
				i++;
				if(map[i][j] == '*') {
					map[i][j] = '.';
					break;
				}
				else if(map[i][j] == '#') {
					break;
				}
			}
		}
		else if(dir == 'L') {
			while(j>=1) {
				j--;
				if(map[i][j] == '*') {
					map[i][j] = '.';
					break;
				}
				else if(map[i][j] == '#') {
					break;
				}
			}
		}
		else if(dir == 'R') {
			while(j<W-1) {
				j++;
				if(map[i][j] == '*') {
					map[i][j] = '.';
					break;
				}
				else if(map[i][j] == '#') {
					break;
				}
			}
		}
	}
}
