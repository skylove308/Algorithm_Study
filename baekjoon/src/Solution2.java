
public class Solution2 {
	static String _reference, _track;
	static int len_r, len_t, ans;

	public static void main(String[] args) {
		//System.out.println(solution("aaaaaa", "aaaaaaaaaaaaaaa"));
		System.out.println(solution("vxrvip", "xrviprvipvxrv"));
	}

	public static int solution(String reference, String track) {
		_reference = reference;
		_track = track;
		len_r = reference.length();
		len_t = track.length();
		dfs(0, 0, false);
		int answer = ans;
		return answer;
	}

	public static void dfs(int idx, int point, boolean check) {
		if (idx == len_t) {
			ans = Math.max(ans, point);
			return;
		}

		for (int i = 0; i < len_r; i++) {
			int j = 0;
			while (true) {
				if (i + j == len_r || idx + j == len_t)
					break;
				if (_reference.charAt(i + j) != _track.charAt(idx + j))
					break;
				j++;

				if (ans <= j) {
					if(!check)
						dfs(idx+j, j, true);
					else
						dfs(idx+j, Math.min(point, j), true);
				}
			}
		}
	}
}
