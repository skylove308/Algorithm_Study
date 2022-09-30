package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12851 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		if (N >= K) {
			System.out.println(N - K);
			System.out.println(1);
		}
			
		else {
			Queue<Integer> q = new LinkedList<>();
			int[] visitTime = new int[100001];
			for (int i = 0; i <= 100000; i++) {
				visitTime[i] = Integer.MAX_VALUE;
			}
			q.add(N);
			int time = 1;
			int cnt = 0;
			visitTime[N] = 0;
			while (!q.isEmpty()) {
				boolean fin = false;
				int size = q.size();
				for (int i = 0; i < size; i++) {
					int p = q.poll();

					if (p == K) {
						fin = true;
						cnt++;
						continue;
					}

					int np;
					np = p - 1;
					if (np >= 0 && np <= 100000 && (visitTime[np] == Integer.MAX_VALUE || visitTime[np] == time)) {
						q.add(np);
						visitTime[np] = time;
					}

					np = p + 1;
					if (np >= 0 && np <= 100000 && (visitTime[np] == Integer.MAX_VALUE || visitTime[np] == time)) {
						q.add(np);
						visitTime[np] = time;
					}

					np = p * 2;
					if (np >= 0 && np <= 100000 && (visitTime[np] == Integer.MAX_VALUE || visitTime[np] == time)) {
						q.add(np);
						visitTime[np] = time;
					}

				}

				if (fin)
					break;

				time++;

			}

			System.out.println(visitTime[K]);
			System.out.println(cnt);

		}
	}
}
