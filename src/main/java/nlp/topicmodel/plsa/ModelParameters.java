package nlp.topicmodel.plsa;

import nlp.common.util.FileUtil;

import java.util.ArrayList;

public class ModelParameters {
    public enum parameters{
        topicNum, iteration, saveStep, beginSaveIters;
    }
    int topicNum = 100;
    int iteration = 100;
    int saveStep = 10;
    int beginSaveIters = 50;

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
}

