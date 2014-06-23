package nlp.topicmodel.tlda;

import java.util.ArrayList;
import java.util.HashMap;



//import edu.mit.jwi.item.Word;

public class User {

	protected String userID;
	
	protected int tweetCnt;
	
	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	
	public User(String Dir, String id, HashMap<String, Integer> wordMap, 
			ArrayList<String> uniWordMap) {
		
		this.userID = id;
		ArrayList<String> datalines = new ArrayList<String>();
		FileUtil.readLines(Dir, datalines);		
		
		this.tweetCnt = datalines.size();
		
		for(int lineNo = 0; lineNo < datalines.size(); lineNo++) {
			String line = datalines.get(lineNo);
			Tweet tw = new Tweet(line, wordMap, uniWordMap);
			tweets.add(tw);						
		}
		
		datalines.clear();
	}
}
