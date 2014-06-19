package nlp.topicmodel.tlda;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


import nlp.common.util.chinese.ChineseTraditionalTokenizator; 
import nlp.common.util.chinese.ChineseStopWords;


//import Common.Stopwords;


//import edu.mit.jwi.item.Word;

public class Tweet {

	protected int time;

	protected int[] tweetwords;

	public Tweet(String dataline, HashMap<String, Integer> wordMap,
			ArrayList<String> uniWordMap) {

		int number = wordMap.size();

		// String inline = dataline.substring(20);
		String inline = dataline.replaceAll("(//)?@([^:^@])*:?", "").replaceAll("[a-z]|[A-Z]|[0-9]", ""); // no specific restriction of input data

		ArrayList<Integer> words = new ArrayList<Integer>();
		ArrayList<String> tokens = new ArrayList<String>();

		//ComUtil.tokenize(inline, tokens);
		try {
			ChineseTraditionalTokenizator.getInstance().tokenizeAndLowerCase(inline, tokens);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ChineseStopWords.init();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < tokens.size(); i++) {
			String tmpToken = tokens.get(i).toLowerCase();
			if (!ChineseStopWords.isStopWord(tmpToken) && !isNoisy(tmpToken) && tmpToken.length()>1) {
				if (!wordMap.containsKey(tmpToken)) {
					words.add(number);
					wordMap.put(tmpToken, number++);
					uniWordMap.add(tmpToken);
				} else {
					words.add(wordMap.get(tmpToken));
				}
			}
			
		}

		tweetwords = new int[words.size()];

		for (int w = 0; w < words.size(); w++) {
			tweetwords[w] = words.get(w);
		}
		words.clear();
		tokens.clear();

	}

	private boolean isNoisy(String token) {
		if (token.toLowerCase().contains("#pb#")
				|| token.toLowerCase().contains("http"))
			return true;
		if (token.contains("@")) {
			return true;
		}
		return token.matches("[\\p{Punct}]+");
	}

}
