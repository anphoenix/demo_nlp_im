package nlp.common.util.chinese;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class ChineseTokenizerTest extends TestCase {
    @Test
    public void testTokenizeAndLowerCase() throws Exception
    {
        String line = "7日消息，当地宣传部证实，太湖快艇事故驾驶员涉嫌危险驾驶，" +
                "已被刑拘。在现场视频中，学生发现“少一个人”后，苦苦哀求出船搜救，" +
                "但该驾驶员连说10来个“没有”，只顾隐瞒真相、逃避责任，拒绝找人。";
        ArrayList <String> tokens = new ArrayList<String>();
        ChineseTokenizer.getInstance().tokenizeAndLowerCase(line, tokens);
        for (int i=0 ; i<tokens.size(); i++)
        {
            System.out.print(tokens.get(i) + " | ");
        }
        System.out.println();
    }
    @Test
    public void testTokenizeWithPOS() throws Exception
    {
        //String line = "日本时事通讯社19日报道称，中国正在探讨在习近平访韩前后，" +
                //"向韩国出借大熊猫，如果实现这将成为“象征两国蜜月关系”的事情。报道" +
                //"称，在中国因为东海和南海与周边国家及美国加深对立之际，中国旨在" +
                //"通过加强和韩国之间的联合牵制日美。日本“外交家”网站18日发表文章" +
                //"称，中韩都是民族自尊心很强的国家，肯定要借此访向世界、向西方、向" +
                //"美国展示，他们对彼此来说有多么重要，并提醒世界对他们的交往多加关注。" +
                //"而两国最后肯定会作出诸如“日本开始重新武装，阴谋再次侵占亚洲”的宣言。";
        String line = "早安当回事这个中介真不是东西";
        ArrayList<String> tokens = new ArrayList<String>();
        ArrayList<String> pos = new ArrayList<String>();
        ChineseTokenizer.getInstance().tokenizeWithPOS(line, tokens, pos);

        System.out.println();
        System.out.println(tokens.toString());
        System.out.println(pos.toString());

        HashMap<String, String> tokensmap = new HashMap<String,String>();
        ChineseTokenizer.getInstance().tokenizeWithPOS(line, tokensmap,false);
        System.out.println(tokensmap.toString());
        System.out.println();
    }
    @Test
    public void testFilterTokenByPOS() throws Exception
    {
        String line = "早安最近一年空气污染指数城市排行。（数据来源：环保部）你在哪个城市？空气可好？";
        HashMap<String, String> tokensmap = new HashMap<String,String>();
        ChineseTokenizer.getInstance().tokenizeWithPOS(line, tokensmap, true);
        ChineseTokenizer.getInstance().filterTokenByPOS(tokensmap);
        System.out.println(tokensmap.toString());
        System.out.println();
    }

}