import java.util.StringTokenizer;

public class test1 {
	public static void main(String[] args) {
		
	}
    public int[] solution(String logs) {
    	int[] ans = new int[24];
        StringTokenizer st = new StringTokenizer(logs,"\n");

        while(st.hasMoreTokens()) {
        	int hour = (Integer.parseInt(st.nextToken().substring(11, 13)) + 9) % 24;
        	ans[hour]++;
        }
        
        return ans;
    }
}
