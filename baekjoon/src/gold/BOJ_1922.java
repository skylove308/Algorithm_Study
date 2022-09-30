package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1922 {
	static int n, m;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] e = new int[m][3];
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			e[i][0] = Integer.parseInt(st.nextToken());
			e[i][1] = Integer.parseInt(st.nextToken());
			e[i][2] = Integer.parseInt(st.nextToken());
		}
		parent = new int[n+1];
		int cost = 0;
		Arrays.sort(e, (o1, o2) -> {
			return o1[2] - o2[2];
		});
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		int cnt = 0;
		for (int i = 0; i < m; i++) {
			if (cnt == n - 1)
				break;
			if (find(e[i][0]) != find(e[i][1])) {
				union(e[i][0], e[i][1]);
				cost += e[i][2];
				cnt++;
			}
		}
		System.out.println(cost);

	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}
}
