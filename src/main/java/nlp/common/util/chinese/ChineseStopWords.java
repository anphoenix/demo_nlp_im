package nlp.common.util.chinese;

import nlp.topicmodel.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChineseStopWords {
	private static File stopWords;
	private static ArrayList<String> SWList;
	public static ChineseStopWords INSTANCE = new ChineseStopWords();
	
	private ChineseStopWords()  {
		stopWords = new File(Config.CH_STOPWORDS_FILE); //read the stop-word library
		SWList = new ArrayList<String>();
		try {
			Scanner scan = new Scanner(stopWords);
			while (scan.hasNextLine()) {
				SWList.add(scan.nextLine()); //put all the stop words into an arraylist
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public boolean isStopWord(String word) {
		boolean result = false;
		if (word.equals("")) {
			result = true;
		} else {
			for (int i = 0; i < SWList.size(); i++){ 
				if (word.equals(SWList.get(i))) { //compare the word to every stop word in the list
					result = true;
					break;
				}
			}
		}
		return result;
	}
	

}
