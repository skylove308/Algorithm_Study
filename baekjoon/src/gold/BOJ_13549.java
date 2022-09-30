package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13549 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		if (N >= K) {
			System.out.println(N - K);
		} else {
			int[] visitTime = new int[100001];
			for (int i = 0; i <= 100000; i++) {
				visitTime[i] = Integer.MAX_VALUE;
			}
			PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
				return visitTime[o1] - visitTime[o2];
			});

			q.add(N);
			visitTime[N] = 0;
			while (!q.isEmpty()) {
				int p = q.poll();
				// System.out.println(p+" "+visitTime[p]);
				
				if(visitTime[p] > visitTime[K])
					continue;
				
				if (p == K)
					continue;
				
				if (p == 0) {
					if (visitTime[1] == Integer.MAX_VALUE || visitTime[1] >= visitTime[0] + 1) {
						q.add(1);
						visitTime[1] = visitTime[0] + 1;
					}
					continue;
				}
				
				for (int i = 0; i < 3; i++) {
					int np = 0;
					if (i == 0)
						np = p - 1;
					if (i == 1)
						np = p + 1;
					if (i == 2)
						np = p * 2;

					if (np < 0 || np > 100000)
						continue;

					if (i != 2 && (visitTime[np] == Integer.MAX_VALUE || visitTime[np] >= visitTime[p] + 1)) {
						q.add(np);
						visitTime[np] = visitTime[p] + 1;
					}

					if (i == 2 && (visitTime[np] == Integer.MAX_VALUE || visitTime[np] >= visitTime[p])) {
						q.add(np);
						visitTime[np] = visitTime[p];
					}
				}
			}
			System.out.println(visitTime[K]);
		}
	}
}
