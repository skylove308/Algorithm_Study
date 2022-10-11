package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_8982 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int water = 0;
		int[][] line = new int[N / 2 + 1][4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		line[0][1] = Integer.parseInt(st.nextToken());
		line[0][0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N / 2; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			line[i][0] = x1;
			line[i][1] = y1;
			line[i][2] = x2;
			line[i][3] = y2;
		}
		st = new StringTokenizer(br.readLine());
		line[N / 2][1] = Integer.parseInt(st.nextToken());
		line[N / 2][0] = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(br.readLine());
		int[][] hole = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			for (int j = 1; j < N / 2; j++) {
				if (line[j][0] == x1 && line[j][1] == y1 && line[j][2] == x2 && line[j][3] == y2) {
					hole[i][0] = j;
					hole[i][1] = Math.min(line[j - 1][0], x1);
					hole[i][2] = Math.min(line[j + 1][0], x1);
				}
			}
		}

		Arrays.sort(hole, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		for(int i=0; i<K; i++) {
			if(i==0) {
				int h = hole[i][1];
				for(int j=hole[i][0]-1; j>=1; j--) {
					if(line[j][0] > h)
						water += (line[j][3] - line[j][1]) * (line[j][0] - h);
					h = Math.min(h, line[j][0]);
				}
			}else {
				int h = Math.max(hole[i-1][2], hole[i][1]);
				for(int j=hole[i][0]-1; j>hole[i-1][0]; j--) {
					if(line[j][0] > h)
						water += (line[j][3] - line[j][1]) * (line[j][0] - h);
					h = Math.min(h, line[j][0]);
				}
			}
		}

//		for (int i = 0; i < K; i++) {
//			System.out.println(hole[i][0] + " " + hole[i][1] + " "+ hole[i][2]);
//		}
		System.out.println(water);
	}
}
