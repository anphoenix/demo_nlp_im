package nlp.topicmodel.lda;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import nlp.common.model.Documents;
import nlp.common.util.FileUtil;
import nlp.topicmodel.Config;

public class LDATest {
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String originalDocsPath = Config.DOCPATH;
		String resultPath = Config.LDA_RESULT;
		String parameterFile= Config.LDA_PARAMETERFILE;

        ModelParameters ldaparameters = new ModelParameters();
        ldaparameters.getParametersFromFile(ldaparameters, parameterFile);
		Documents docSet = new Documents();
		docSet.readDocs(originalDocsPath);
		System.out.println("wordMap size " + docSet.termToIndexMap.size());
		FileUtil.mkdir(new File(resultPath));
		LDAModel model = new LDAModel(ldaparameters);
		System.out.println("1 Initialize the model ...");
		model.initializeModel(docSet);
		System.out.println("2 Learning and Saving the model ...");
		model.inferenceModel(docSet);
		System.out.println("3 Output the final model ...");
		model.saveIteratedModel(ldaparameters.iteration, docSet);
		System.out.println("Done!");
	}
}
