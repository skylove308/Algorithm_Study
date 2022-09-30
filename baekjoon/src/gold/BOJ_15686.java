package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686 {
	static int n, m, min;
	static List<int[]> chicken;
	static int[][] chicken2;
	static List<int[]> house;
	static int[][] city, city2;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // N * N 도시 사이즈
		m = Integer.parseInt(st.nextToken()); // 폐업 시키지 않을 치킨집 갯수
		city = new int[n+1][n+1];
		city2 = new int[n+1][n+1];

		chicken = new ArrayList<>();
		chicken2 = new int[m][2];
		house = new ArrayList<>();
		min = Integer.MAX_VALUE;
		for(int i=1; i<=n; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				city[i][j] = Integer.parseInt(st2.nextToken());
				if(city[i][j] == 1) {
					house.add(new int[] {i, j});
					city2[i][j] = 1;
				}
				if(city[i][j] == 2) {
					chicken.add(new int[] {i, j});
				}
			}
		}
		
		comb(0, 0);
		System.out.println(min);
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == m) {
			// city2 배열 치킨집 없도록 초기화
			for(int i=0; i<chicken.size(); i++) {
				city2[chicken.get(i)[0]][chicken.get(i)[1]] = 0;
			}
			// city2 배열 새로운 치킨집 조합으로 생성되도록 초기화
			for(int i=0; i<m; i++) {
				city2[chicken2[i][0]][chicken2[i][1]] = 2;
			}
			int total_dist = 0;
			for(int i=0; i<house.size(); i++) {
				int pos[] = house.get(i);
				int dist = Integer.MAX_VALUE;
				for(int j=0; j<m; j++) {
					int temp = Math.abs(chicken2[j][0] - pos[0]) + Math.abs(chicken2[j][1] - pos[1]);
					dist = dist < temp ? dist : temp;
				}
				total_dist += dist;
			}
			min = min < total_dist ? min : total_dist;
			return;
		}
		for(int i=idx; i<chicken.size(); i++) {
			chicken2[cnt] = chicken.get(i);
			comb(i+1, cnt+1);
		}
	}
}
