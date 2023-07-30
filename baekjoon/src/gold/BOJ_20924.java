package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20924 {
	static class Tree {
		int v1, v2, e;

		public Tree(int v1, int v2, int e) {
			this.v1 = v1;
			this.v2 = v2;
			this.e = e;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[] len = new int[N + 1]; // 길이 누적합
		LinkedList<Tree>[] Trees = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			Trees[i] = new LinkedList<>();
		}
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int nv = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			Trees[v].add(new Tree(v, nv, e));
			Trees[nv].add(new Tree(nv, v, e));
		}

		Queue<Tree> q = new LinkedList<>();
		for (Tree t : Trees[R]) {
			q.add(t);
		}

		int pillar = 0;
		boolean isPillar = true;
		while (!q.isEmpty()) {
			if (isPillar && q.size() == 1) {
				Tree t = q.poll();
				pillar += t.e;
				len[t.v2] = len[t.v1] + t.e;
				for (Tree nt : Trees[t.v2]) {
					if(nt.v2 != t.v1)
						q.add(nt);
				}
				continue;
			}

			if (isPillar && q.size() > 1)
				isPillar = false;

			Tree t = q.poll();
			len[t.v2] = len[t.v1] + t.e;
			for (Tree nt : Trees[t.v2]) {
				if(nt.v2 != t.v1)
					q.add(nt);
			}

		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, len[i]);
		}
		
		max -= pillar;

		System.out.println(pillar + " " + max);
	}
}
