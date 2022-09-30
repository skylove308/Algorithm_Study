package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_1238 {
	static ArrayList<Integer>[] data;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		int test_case = 1;
		while((s = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			data = new ArrayList[101];
			for(int i=0; i<101; i++) {
				data[i] = new ArrayList<>();
			}
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int i=0; i<n/2; i++) {
				data[Integer.parseInt(st2.nextToken())].add(Integer.parseInt(st2.nextToken()));
			}
			visited = new boolean[101];
			System.out.println("#"+test_case+" "+bfs(start));
			test_case++;
		}
	}
	
	static int bfs(int idx) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {idx, 0});
		visited[idx] = true;
		int max = 0;
		int max_num = 0;
		while(!q.isEmpty()) {
			int[] x = q.poll();
			for(int i=0; i<data[x[0]].size(); i++) {
				if(visited[data[x[0]].get(i)]) continue;
				visited[data[x[0]].get(i)] = true;
				q.add(new int[] {data[x[0]].get(i), x[1]+1});
				if(x[1]+1 > max) {
					max = x[1]+1;
					max_num = data[x[0]].get(i);

				}
				else if(x[1]+1 == max) {
					max_num = Math.max(max_num, data[x[0]].get(i));
				}
			}
		}
		return max_num;
	}
}
