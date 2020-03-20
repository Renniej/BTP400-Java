


import java.util.ArrayList;



public class threadingApp {
	
	static String WORD = "java";
	static int counter = 0;
	
	public static void main(String args []) throws InterruptedException {
		

	String [][] data = {               
			{ "java", "I love java", "c++", "python" },           
			{ "c programs", "cookies",  "java developers", "oops"},           
			{ "john", "doe", "mary", "mark holmes"},           
			{ "hello java java", "byebye", "java again?", "java what?"},           
			{ "toronto", "montreal", "quebec city", "calgary"}     
			};
	


	Thread[] threads = new Thread[data.length];
	
	threadSearch[] sThreads = new threadSearch[data.length];
	
	
	
	ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
	

	
    for (int i=0; i < data.length; ++i) {
    	sThreads[i] = new threadSearch(data[i]); 
    }
	
	
	for (int i=0; i < data.length; ++i) {
         threads[i] = new Thread(sThreads[i]);
         threads[i].start();
    }

  
    
	
	
	for (Thread thread: threads ) {
		 thread.join();
	}
	
 
	for (threadSearch sThread : sThreads) {
		 results.add(sThread.getResult());
	}
	
   
    
    System.out.println("+ search word : " + WORD + "\n"
    		+ 			"+ summary :" + "\n"
    		
    		);
    
    
  
    for(int i = 0; i < data.length; ++i) {
    	
    	if(results.get(i).size() == 0) 
    		System.out.println("row " + (i+1) + ": " + "***");
    	else 
    		System.out.println("row " + (i+1) + ": " + results.get(i));
    	
    }
    
    System.out.println("+ total number of strings found: " + counter);
    
  }
}
