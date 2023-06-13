
public class test3 {
	public static void main(String[] args) {

	}

	public int solution(int[] rooms) {
		int s = rooms.length;
		boolean[] visited = new boolean[s];
		int answer = 0;

		for (int i = 0; i < s; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			int next_room = rooms[i] - 1;
			while (!visited[next_room]) {
				visited[next_room] = true;
				next_room = rooms[next_room] - 1;
			}

			answer++;
		}

		if (answer > 1) {
			answer--;
		}

		return answer;
	}

	public void dfs(int[] rooms, int idx) {

	}
}
