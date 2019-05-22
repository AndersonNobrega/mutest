package org.lar.MutationSetup;

import org.antlr.v4.runtime.misc.MultiMap;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.commons.io.FileUtils;
import org.lar.FileUtils.FileBrowser;
import org.lar.FileUtils.FileCreator;
import org.lar.MutationSetup.Language.LanguageWalker;
import org.lar.MutationSetup.MutationOperators.Operator;

import java.io.File;
import java.io.IOException;

public class Init {
    public void start(String[] operators, String dirPath, String savePath) {
        for(String operator : operators) {
            Operator mutationOperator = OperatorFactory.getOperator(operator);
            LanguageWalker walker = LanguageWalkerFactory.getWalker("SV");
            try {
                FileBrowser.browseDirectory(new File(dirPath), mutationOperator, walker);
            } catch (IOException error) {
                System.out.println("Path not found");
            }
        }

        saveMutations(dirPath, savePath);
    }

    private void saveMutations(String dirPath, String savePath) {
        MultiMap<String, MultiMap<String, String>> mutantList = FileBrowser.getMutations();
        int cont;
        String newPath;

        if(mutantList.isEmpty()) {
            System.out.println("No mutants were created.");
        } else {
            for(String key : mutantList.keySet()) {
                cont = 0;
                for(MultiMap<String, String> fileMap : mutantList.get(key)) {
                    for(Pair fileMutant : fileMap.getPairs()) {
                        cont++;
                        try {
                            String file = (String) fileMutant.a;
                            String mutant = (String) fileMutant.b;
                            newPath = "/ProjectMutant/" + key + "/" + cont + "/";

                            FileUtils.copyDirectory(new File(dirPath), new File(savePath  + newPath));
                            FileCreator.saveFile(file, savePath  + newPath, mutant, "sv");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
