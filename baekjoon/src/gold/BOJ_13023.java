package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023 {
	static ArrayList<Integer>[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			arr[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[b].add(a);
		}

		boolean check = false;
		for (int i = 0; i < n; i++) {
			visited = new boolean[n];
			visited[i] = true;
			if (dfs(i, 0)) {
				check = true;
				break;
			}
		}
		if (check)
			System.out.println(1);
		else
			System.out.println(0);
	}

	static boolean dfs(int x, int cnt) {
		if (cnt == 4)
			return true;
		for (int i = 0; i < arr[x].size(); i++) {
			if (visited[arr[x].get(i)])
				continue;
			visited[arr[x].get(i)] = true;
			if (dfs(arr[x].get(i), cnt + 1))
				return true;
			visited[arr[x].get(i)] = false;
		}
		return false;
	}
}
