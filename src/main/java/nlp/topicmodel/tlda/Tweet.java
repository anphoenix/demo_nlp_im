package nlp.topicmodel.tlda;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import nlp.common.util.chinese.ChineseTokenizer;
import nlp.common.util.chinese.ChineseStopWords;


//import Common.Stopwords;


//import edu.mit.jwi.item.Word;

public class Tweet {

	protected int time;
    public static int n =1;
	protected int[] tweetwords;

	public Tweet(String dataline, HashMap<String, Integer> wordMap,
			ArrayList<String> uniWordMap) {

		int number = wordMap.size();

		String inline = dataline.replaceAll("(//)?@([^:^@^ ^，^：])*:?", "").replaceAll("[a-z]|[A-Z]|[0-9]", ""); // no specific restriction of input data

		ArrayList<Integer> words = new ArrayList<Integer>();
		//ArrayList<String> tokens = new ArrayList<String>();
        HashMap<String, String> tokens = new HashMap<String, String>();
		//ComUtil.tokenize(inline, tokens);

		try {
			//ChineseTokenizer.getInstance().tokenizeAndLowerCase(inline, tokens);
            //System.out.println(n + inline);
            n++;
            ChineseTokenizer.getInstance().tokenizeWithPOS(inline, tokens, false);
            ChineseTokenizer.getInstance().filterTokenByPOS(tokens);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        for (Map.Entry<String, String> entry : tokens.entrySet())
        {
			String tmpToken = entry.getKey();
			if (!ChineseStopWords.INSTANCE.isStopWord(tmpToken) && !isNoisy(tmpToken) ) {
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
