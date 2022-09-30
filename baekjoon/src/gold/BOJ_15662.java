package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15662 {
	static int T;
	static int[][] wheels;
	static int[] rotate;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		wheels = new int[T + 1][8];

		for (int i = 1; i <= T; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheels[i][j] = s.charAt(j) - '0';
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			rotate = new int[T + 1];
			rotate[N] = R;
			rotate_left(N, R);
			rotate_right(N, R);
			
			for (int j = 1; j <= T; j++) {
				if (rotate[j] == -1)
					rotate_counter_clockwise(wheels[j]);
				else if (rotate[j] == 1)
					rotate_clockwise(wheels[j]);
			}
			
			//print();
		}
		
		int ans = 0;
		for (int i = 1; i <= T; i++) {
			if (wheels[i][0] == 1)
				ans++;
		}
		System.out.println(ans);
	}

	static void rotate_left(int n, int r) {
		if (n > 1 && wheels[n][6] != wheels[n - 1][2]) {
			if (r == -1) {
				rotate[n - 1] = 1;
				rotate_left(n - 1, 1);
			} else if (r == 1) {
				rotate[n - 1] = -1;
				rotate_left(n - 1, -1);
			}
		}
	}
	
	static void rotate_right(int n, int r) {
		if (n < T && wheels[n][2] != wheels[n + 1][6]) {
			if (r == -1) {
				rotate[n + 1] = 1;
				rotate_right(n + 1, 1);
			} else if (r == 1) {
				rotate[n + 1] = -1;
				rotate_right(n + 1, -1);
			}
		}
	}

	static void rotate_counter_clockwise(int[] wheel) {
		int temp = wheel[0];
		for (int i = 0; i < 7; i++) {
			wheel[i] = wheel[i + 1];
		}
		wheel[7] = temp;
	}

	static void rotate_clockwise(int[] wheel) {
		int temp = wheel[7];
		for (int i = 6; i >= 0; i--) {
			wheel[i + 1] = wheel[i];
		}
		wheel[0] = temp;
	}
	
	static void print() {
		for(int i=1; i<=T; i++) {
			for(int j=0; j<8; j++) {
				System.out.print(wheels[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
