package nlp.common.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nlp.common.util.MD5;
import nlp.common.util.chinese.ChineseStopWords;
import nlp.common.util.chinese.ChineseTraditionalTokenizator;
import nlp.common.util.FileUtil;

public class Documents {
	
	public ArrayList<Document> docs;
	public Map<String, Integer> termToIndexMap;
	public ArrayList<String> indexToTermMap;
	public Map<String,Integer> termCountMap;
	
	public Documents(){
		docs = new ArrayList<Document>();
		termToIndexMap = new HashMap<String, Integer>();
		indexToTermMap = new ArrayList<String>();
		termCountMap = new HashMap<String, Integer>();
	}
	
	public void readDocs(String docsPath, boolean byline){
        try {
            ChineseStopWords.init();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		for(File docFile : new File(docsPath).listFiles()){
            if(byline){
                ArrayList<String> docLines = new ArrayList<String>();
                FileUtil.readLines(docFile.getAbsolutePath(), docLines);
                for(String line : docLines){
                    Document doc = new Document(line, termToIndexMap, indexToTermMap, termCountMap, byline);
                    docs.add(doc);
                }

            } else {
                Document doc = new Document(docFile.getAbsolutePath(), termToIndexMap, indexToTermMap, termCountMap);
                docs.add(doc);
            }

		}
	}
	
	public static class Document {	
		private String docName;
		public int[] docWords;
		
		public Document(String docName, Map<String, Integer> termToIndexMap, ArrayList<String> indexToTermMap, Map<String, Integer> termCountMap){
			this.docName = docName;
			//Read file and initialize word index array
			ArrayList<String> docLines = new ArrayList<String>();
			ArrayList<String> words = new ArrayList<String>();
			FileUtil.readLines(docName, docLines);
			for(String line : docLines){
				try {
					ChineseTraditionalTokenizator.tokenizeAndLowerCase(line, words);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//Remove stop words and noise words
			for(int i = 0; i < words.size(); i++){
				if(ChineseStopWords.isStopWord(words.get(i).replaceAll(" ", "").replaceAll("　", ""))){
					words.remove(i);
					i--;
				}
			}
			//Transfer word to index
			this.docWords = new int[words.size()];
            this.buildIndex(words, termToIndexMap, indexToTermMap, termCountMap);
//			for(int i = 0; i < words.size(); i++){
//				String word = words.get(i);
//				if(!termToIndexMap.containsKey(word)){
//					int newIndex = termToIndexMap.size();
//					termToIndexMap.put(word, newIndex);
//					indexToTermMap.add(word);
//					termCountMap.put(word, new Integer(1));
//					docWords[i] = newIndex;
//				} else {
//					docWords[i] = termToIndexMap.get(word);
//					termCountMap.put(word, termCountMap.get(word) + 1);
//				}
//			}
			words.clear();
		}

        public Document(String text, Map<String, Integer> termToIndexMap, ArrayList<String> indexToTermMap, Map<String, Integer> termCountMap, boolean byline){
            this.docName = MD5.GetMD5Code(text);
            //Read file and initialize word index array
            ArrayList<String> words = new ArrayList<String>();

            try {
                ChineseTraditionalTokenizator.tokenizeAndLowerCase(text, words);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Remove stop words and noise words
            for(int i = 0; i < words.size(); i++){
                if(ChineseStopWords.isStopWord(words.get(i).replaceAll(" ", "").replaceAll("　", ""))){
                    words.remove(i);
                    i--;
                }
            }
            //Transfer word to index
            this.docWords = new int[words.size()];
            this.buildIndex(words, termToIndexMap, indexToTermMap, termCountMap);
            words.clear();
        }

        private void buildIndex(ArrayList<String> words, Map<String, Integer> termToIndexMap, ArrayList<String> indexToTermMap, Map<String, Integer> termCountMap){
            for(int i = 0; i < words.size(); i++){
                String word = words.get(i);
                if(!termToIndexMap.containsKey(word)){
                    int newIndex = termToIndexMap.size();
                    termToIndexMap.put(word, newIndex);
                    indexToTermMap.add(word);
                    termCountMap.put(word, new Integer(1));
                    docWords[i] = newIndex;
                } else {
                    docWords[i] = termToIndexMap.get(word);
                    termCountMap.put(word, termCountMap.get(word) + 1);
                }
            }
        }
		
		public boolean isNoiseWord(String string) {
			// TODO Auto-generated method stub
			string = string.toLowerCase().trim();
			Pattern MY_PATTERN = Pattern.compile(".*[a-zA-Z]+.*");
			Matcher m = MY_PATTERN.matcher(string);
			// filter @xxx and URL
			if(string.matches(".*www\\..*") || string.matches(".*\\.com.*") || 
					string.matches(".*http:.*") )
				return true;
			if (!m.matches()) {
				return true;
			} else
				return false;
		}
		
	}
}
