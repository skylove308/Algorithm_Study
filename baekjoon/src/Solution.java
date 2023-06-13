import java.util.ArrayList;

public class Solution {
	static int[] marbles_arr;
	static int[] _marbles;
	static int N;
	static ArrayList<Integer> max;
	static int max_idx;
	static boolean[] used;


	static public int[] solution(int[] marbles) {
		N = marbles.length;
		marbles_arr = new int[N + 1];
		max = new ArrayList<>();
		_marbles = new int[N + 1];
		for (int i = 0; i < N; i++) {
			_marbles[i + 1] = marbles[i];
		}
		used = new boolean[N + 1];
		perm(1);
		int s = max.size();
		int[] answer = new int[s];
		for (int i = 0; i < s; i++) {
			answer[i] = max.get(i);
		}
		return answer;
	}

	static public void perm(int idx) {
		if (idx == N + 1) {
			ArrayList<Integer> marbles = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if (marbles_arr[i] != 0) {
					marbles.add(_marbles[marbles_arr[i]]);
				}
			}

			if (marbles.isEmpty())
				return;

			int size = marbles.size();

			for (int i = 0; i < size; i++) {
				if (compare_weight(marbles, i)) {
					if (max.isEmpty() || compare(marbles, max, i, max_idx)) {
						max.clear();
						for (int j = 0; j < size; j++) {
							max.add(marbles.get(j));
						}
						max_idx = i;
					}
				}
			}

			return;
		}

		for (int i = 0; i <= N; i++) {
			if (used[i])
				continue;
			marbles_arr[idx] = i;
			if (i != 0)
				used[i] = true;
			perm(idx + 1);
			used[i] = false;
		}
	}

	static public boolean compare(ArrayList<Integer> m1, ArrayList<Integer> m2, int idx, int m_idx) {
		int s1 = m1.size();
		int s2 = m2.size();
		if (diff_marbles(s1, idx) > diff_marbles(s2, m_idx))
			return true;

		if (diff_marbles(s1, idx) < diff_marbles(s2, m_idx))
			return false;

		if (s1 > s2)
			return true;

		if (s1 < s2)
			return false;

		int w1 = sum_weight(m1);
		int w2 = sum_weight(m2);

		if (w1 > w2)
			return true;

		if (w1 < w2)
			return false;

		for (int i = 0; i < s1; i++) {
			if (m1.get(i) < m2.get(i))
				return true;
			if (m1.get(i) > m2.get(i))
				return false;
		}

		return true;

	}

	static public int diff_marbles(int s, int idx) {
		if (s % 2 == 0) {
			return s - 2 * idx;
		} else {
			return s - 2 * idx - 1;
		}
	}

	static public int sum_weight(ArrayList<Integer> m) {
		int sum = 0;
		for (int w : m) {
			sum += w;
		}
		return sum;
	}

	static public boolean compare_weight(ArrayList<Integer> m, int idx) {
		int size = m.size();
		int w1 = 0;
		int w2 = 0;
		if (m.size() % 2 == 0) {
			for (int i = 0; i < idx; i++) {
				w1 += m.get(i);
			}
			for (int i = idx; i < size; i++) {
				w2 += m.get(i);
			}
		} else {
			for (int i = 0; i < idx; i++) {
				w1 += m.get(i);
			}
			for (int i = idx + 1; i < size; i++) {
				w2 += m.get(i);
			}
		}
		return w1 == w2;
	}
}
