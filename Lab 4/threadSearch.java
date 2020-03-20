
import java.util.ArrayList;
import java.util.Random;



public class threadSearch implements Runnable{
	
	private String[] strArr;
	
	private ArrayList<String> results;
	
	
	public threadSearch() {
		strArr = null;
		results = new ArrayList<String>();;
	}

	public threadSearch(String[] strings) {
	
		strArr = strings;
		results = new ArrayList<String>();
	}
	
	
	public ArrayList<String> getResult() {
		return results;
	}
	
	
	
	public void run() {
		Random random = new Random();
		
		//int index = 0;
		
		for (String word : strArr) {
			if(word.contains(threadingApp.WORD)) {
				
				results.add(word);
				threadingApp.counter++;
			}
			
			
			
		}
		
		
		int max = 2000;
		int min = 100;
		int time = random.nextInt(max-min) + min;
		
		try{
			Thread.sleep(time);
		}catch(InterruptedException e) {
			
		}
		
	}
	
	
	

}