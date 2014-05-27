package liuyang.nlp.lda.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import liuyang.nlp.lda.conf.ConstantConfig;

public class ChineseStopWords {
	private static File stopWords;
	private static ArrayList<String> SWList;
	
	public static void init() throws FileNotFoundException {
		stopWords = new File(ConstantConfig.CH_STOPWORDS_FILE); //read the stop-word library
		Scanner scan = new Scanner(stopWords);
		SWList = new ArrayList<String>();
		
		while (scan.hasNextLine()) {
			SWList.add(scan.nextLine()); //put all the stop words into an arraylist
		}
	}
	
	public static boolean isStopWord(String word) {
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
