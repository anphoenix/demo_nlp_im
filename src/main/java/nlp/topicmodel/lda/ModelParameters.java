package nlp.topicmodel.lda;

import nlp.common.util.FileUtil;

import java.util.ArrayList;

public class ModelParameters {
    public enum parameters{
        alpha, beta, topicNum, iteration, saveStep, beginSaveIters;
    }

    float alpha = 0.5f; //usual value is 50 / K
    float beta = 0.1f;//usual value is 0.1
    int topicNum = 100;
    int iteration = 100;
    int saveStep = 10;
    int beginSaveIters = 50;

    /**
     * Get parameters from configuring file. If the
     * configuring file has value in it, use the value.
     * Else the default value in program will be used
     *
     * @param ldaparameters
     * @param parameterFile
     * @return void
     */
    public static void getParametersFromFile(ModelParameters ldaparameters,
                                              String parameterFile) {
        // TODO Auto-generated method stub
        ArrayList<String> paramLines = new ArrayList<String>();
        FileUtil.readLines(parameterFile, paramLines);
        for (String line : paramLines) {
            String[] lineParts = line.split("\t");
            switch (parameters.valueOf(lineParts[0])) {
                case alpha:
                    ldaparameters.alpha = Float.valueOf(lineParts[1]);
                    break;
                case beta:
                    ldaparameters.beta = Float.valueOf(lineParts[1]);
                    break;
                case topicNum:
                    ldaparameters.topicNum = Integer.valueOf(lineParts[1]);
                    break;
                case iteration:
                    ldaparameters.iteration = Integer.valueOf(lineParts[1]);
                    break;
                case saveStep:
                    ldaparameters.saveStep = Integer.valueOf(lineParts[1]);
                    break;
                case beginSaveIters:
                    ldaparameters.beginSaveIters = Integer.valueOf(lineParts[1]);
                    break;
            }
        }
    }
}


