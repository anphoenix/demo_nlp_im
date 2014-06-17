package nlp.common.util.chinese;

import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;



public class ChineseTraditionalTokenizator {

public static ChineseTraditionalTokenizator INSTANCE = new ChineseTraditionalTokenizator();


	private ChineseTraditionalTokenizator(){

	}
	
	public static void tokenizeAndLowerCase(String line, ArrayList<String> tokens) throws IOException
	{
		StringReader sr=new StringReader(line);
		IKSegmentation iks=new IKSegmentation(sr, true);
		Lexeme lex=null;
		while((lex=iks.next())!=null){
			//System.out.print(lex.getLexemeText()+"|");
			tokens.add(lex.getLexemeText());
		}
		sr.close();
		
	}

	
	public static void main(String[] args) throws IOException {
		//ChineseTraditionalTokenizator ctt = new ChineseTraditionalTokenizator();
		//String str="习近平指出日本在此事件中的强词夺理在其矛盾说辞中可见一斑。据共同社报道，日本外务省事务次官斋木昭隆26日召见中国驻日本大使程永华，就军机接近一事提出抗议。";
		String str = "可怜的@老沉啊。你们开个微博给你自己添了多少麻烦，给领导添了多少的麻烦。回到过去多好，但回不去了。洗洗睡吧。";
        StringReader sr=new StringReader(str);
        IKSegmentation ik=new IKSegmentation(sr, true);
		Lexeme lex=null;
		while((lex=ik.next())!=null){
			System.out.print(lex.getLexemeText()+"|");
		}
		sr.close();
		System.out.println();

		//System.out.println(ctt.getTokenList(str).toString());
		//List<String> textTokenList = ChineseTraditionalTokenizator.INSTANCE.getTokenList(str);

		//System.out.println(textTokenList);
		

		
	}
}
