package nlp.topicmodel.plsa;

import java.io.IOException;

import nlp.common.model.Documents;
import nlp.topicmodel.Config;


public class PLSATest {


    public static void main(String[] args) throws IOException {
    	Documents docSet = new Documents();
    	System.out.println("0 Read Docs ...");
    	String parameterFile= Config.PLSA_PARAMETERFILE;
    	ModelParameters plsaparameters = new ModelParameters();
        plsaparameters.getParametersFromFile(plsaparameters, parameterFile);
    	PLSAModel model = new PLSAModel(plsaparameters);
    	docSet.readDocs(Config.DOCPATH, false);
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
