import java.util.ArrayList;


public class test4 {

	public static void main(String[] args) {

	}

	public int[] solution(int[][] jobs) {
		ArrayList<Integer> answer = new ArrayList<>();

		int idx = 1;
		int size = jobs.length;

		int[][] classify_jobs = new int[101][2];
		int cur_idx = jobs[0][2];
		classify_jobs[cur_idx][0] += jobs[0][3];
		classify_jobs[cur_idx][1] += jobs[0][1];
		answer.add(cur_idx);
		int time = jobs[0][0];
		while (true) {
			int prev_idx = cur_idx;
			
			boolean check = true;
			for(int i=1; i<=100; i++) {
				if(classify_jobs[i][0]!=0) {
					check= false;
					break;
				}
			}
			
			if(check) {
				if(time > jobs[size-1][0])
					break;
				
				cur_idx = jobs[idx][2];
				classify_jobs[cur_idx][0] += jobs[idx][3];
				classify_jobs[cur_idx][1] += jobs[idx][1];
				time = jobs[idx][0];
				idx++;
				if(prev_idx != cur_idx)
					answer.add(cur_idx);
				continue;
			}
				
			int max_important = 0;
			if (classify_jobs[cur_idx][0] == 0) {
				for (int i = 1; i <= 100; i++) {
					if (max_important < classify_jobs[i][0]) {
						max_important = classify_jobs[i][0];
						cur_idx = i;
					}
				}
				
				time += classify_jobs[cur_idx][1];
				classify_jobs[cur_idx][0] = 0;
				classify_jobs[cur_idx][1] = 0;
				
				for (int i = idx; i < size; i++) {
					if (jobs[i][0] > time) 
						break;
					
					classify_jobs[jobs[i][2]][0] += jobs[i][3];
					classify_jobs[jobs[i][2]][1] += jobs[i][1];
					idx++;
				}
				
				if(prev_idx!=cur_idx) {
					answer.add(cur_idx);
				}
			} else {
				time += classify_jobs[cur_idx][1];
				classify_jobs[cur_idx][0] = 0;
				classify_jobs[cur_idx][1] = 0;
				
				for (int i = idx; i < size; i++) {
					if (jobs[i][0] > time) 
						break;
					
					classify_jobs[jobs[i][2]][0] += jobs[i][3];
					classify_jobs[jobs[i][2]][1] += jobs[i][1];
					idx++;
				}
			}

		}

		int[] ans = new int[answer.size()];
		for(int i=0; i<answer.size(); i++) {
			ans[i] = answer.get(i);
		}
		
		return ans;
	}
}
