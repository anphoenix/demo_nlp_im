package nlp.topicmodel.plsa;

import java.io.IOException;
import java.util.ArrayList;

import nlp.common.model.Documents;
import nlp.common.util.FileUtil;
import nlp.topicmodel.Config;


/**
 * Test Class for PLSA model using EM algorithm
 * 
 * @author yangliu
 * @blog http://blog.csdn.net/yangliuy
 * @mail yangliuyx@gmail.com
 */

public class PlsaTest {

	public static class ModelParameters {
		int topicNum = 100;
		int iteration = 100;
		int saveStep = 10;
		int beginSaveIters = 50;
	}
	public static void getParametersFromFile(ModelParameters plsaparameters,
			String parameterFile) {
		// TODO Auto-generated method stub
		ArrayList<String> paramLines = new ArrayList<String>();
		FileUtil.readLines(parameterFile, paramLines);
		for(String line : paramLines){
			String[] lineParts = line.split("\t");
			switch(parameters.valueOf(lineParts[0])){
			case topicNum:
				plsaparameters.topicNum = Integer.valueOf(lineParts[1]);
				break;
			case iteration:
				plsaparameters.iteration = Integer.valueOf(lineParts[1]);
				break;
			case saveStep:
				plsaparameters.saveStep = Integer.valueOf(lineParts[1]);
				break;
			case beginSaveIters:
				plsaparameters.beginSaveIters = Integer.valueOf(lineParts[1]);
				break;
			}
		}
	}
	public enum parameters{
		topicNum, iteration, saveStep, beginSaveIters;
	}
    public static void main(String[] args) throws IOException {
    	Documents docSet = new Documents();
    	System.out.println("0 Read Docs ...");
    	String parameterFile= Config.PLSA_PARAMETERFILE;
    	ModelParameters plsaparameters = new ModelParameters();
    	getParametersFromFile(plsaparameters, parameterFile);
    	Plsa model = new Plsa(plsaparameters);
    	docSet.readDocs(Config.DOCPATH);
    	System.out.println("docSet: " + docSet.docs.size());
		
    	System.out.println("1 Initialize the model ...");
		model.initializeModel(docSet);
		System.out.println("2 Learning and Saving the model ...");
		model.inferenceModel(docSet);
		System.out.println("3 Output the final model ...");
		model.saveIteratedModel(plsaparameters.iteration, docSet);
		System.out.println("Done!");
    }
}
