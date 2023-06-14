package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BOJ_4358 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s ="";
		Map<String, Integer> trees = new TreeMap<>();
		int cnt = 0;
		while((s = br.readLine()) != null && !s.isEmpty()) {
			trees.put(s, trees.getOrDefault(s, 0) + 1);
			cnt++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(Entry<String, Integer> tree : trees.entrySet()) {
			sb.append(tree.getKey());
			sb.append(" ");
			sb.append(String.format("%.4f", (double)tree.getValue()/(double)cnt *100));
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
}
