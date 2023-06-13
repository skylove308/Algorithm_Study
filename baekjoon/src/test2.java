
public class test2 {
	public static void main(String[] args) {

	}

	public int solution(int money, int minratio, int maxratio, int ranksize, int threshold, int months) {
		while(months!=0) {	
			int assume_money = money - money % 100;
			
			if(assume_money < threshold)
				return money;
			
			long ratio = minratio + (assume_money - threshold) / ranksize;
			if(ratio > maxratio)
				ratio = maxratio;
			
			money -= assume_money / 100 * ratio;
			months--;
		}
		
		return money;
	}
}
