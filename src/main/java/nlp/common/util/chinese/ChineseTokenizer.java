package nlp.common.util.chinese;

import edu.fudan.ml.types.Dictionary;
import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.cn.tag.POSTagger;
import nlp.topicmodel.Config;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;


public class ChineseTokenizer {

	private static ChineseTokenizer instance;
    private static POSTagger tag;
    private static HashSet<String> pfSet;
    private static CWSTagger cws;

	private ChineseTokenizer() throws Exception {

        cws = new CWSTagger(Config.CH_MODEL_SEG,new Dictionary(Config.CH_MODEL_DICT));
        tag = new POSTagger(cws, Config.CH_MODEL_POS, new Dictionary(Config.CH_MODEL_DICT), true);

        File posFilter = new File(Config.CH_POS_FILTER); //read

        pfSet = new HashSet<String>();
        try {
            Scanner scan = new Scanner(posFilter);
            while (scan.hasNextLine())
            {
                pfSet.add(scan.nextLine()); //put all the stop words into an hashset
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public static ChineseTokenizer getInstance() throws Exception{
		if(instance == null){
			instance = new ChineseTokenizer();
		} 
		return instance;
	}
	
	public void tokenizeAndLowerCase(String line, ArrayList<String> tokens) throws IOException
	{
		StringReader sr=new StringReader(line);
		IKSegmentation iks=new IKSegmentation(sr, true);
		Lexeme lex=null;
		while((lex=iks.next())!=null){
			tokens.add(lex.getLexemeText());
		}
		sr.close();
	}
    //this method can get the token and pos in a Hashmap
    //like 东西:名词
    public void tokenizeWithPOS(String line, HashMap<String, String> tokens, boolean isTokened) throws Exception
    {
        if(line.length() == 0){
            //System.out.println("emptyline: " + line);
            return;
        }
        if (!isTokened) {
            String[][] temp = tag.tag2Array(line);
            for (int i = 0; i < temp[0].length; i++) {
                tokens.put(temp[0][i], temp[1][i]);
            }
        }
        else
        {
            ArrayList<String> tokenlist = new ArrayList<String>();
            tokenizeAndLowerCase(line,tokenlist);
            String [] temp = new String[tokenlist.size()];
            for (int i=0; i<tokenlist.size(); i++)
            {
                temp[i] = tokenlist.get(i);
            }
            String[] pos = tag.tagSeged(temp);

            for (int i = 0; i < temp.length; i++) {
                tokens.put(temp[i], pos[i]);
            }
            tokenlist.clear();

        }
    }

    //**
    //this method can get two array list
    //tokens:分词结果
    //pos:与分词结果相对应的词
    //**
    public void tokenizeWithPOS (String line, ArrayList<String> tokens, ArrayList<String> pos) throws Exception
    {
        String [][] temp = tag.tag2Array(line);
        for(int i=0; i<temp[0].length; i++)
        {
            tokens.add(temp[0][i]);
            pos.add(temp[1][i]);
        }
    }

    //just keep the token which pos in filter file
    public void filterTokenByPOS(HashMap<String, String> tokens)
    {
        String pos = null;
        Iterator<Map.Entry<String, String>> it = tokens.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry=it.next();
            pos=entry.getValue();
            if(!pfSet.contains(pos))
            {
                it.remove();
            }
        }
        //System.out.print(tokens.toString());
    }

}
