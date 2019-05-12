package org.lar.MutationSetup;

import org.apache.commons.io.FileUtils;
import org.lar.FileUtils.FileBrowser;
import org.lar.FileUtils.FileCreator;
import org.lar.FileUtils.MultiMap;
import org.lar.MutationSetup.MutationOperators.Operator;

import java.io.File;
import java.io.IOException;

public class Init {
    public void start(String[] operators, String dirPath, String savePath) {
        for(String operator : operators) {
            Operator mutationOperator = OperatorFactory.getOperator(operator);
            try {
                FileBrowser.browseDirectory(new File(dirPath), mutationOperator);
            } catch (IOException error) {
                System.out.println("Path not found");
            }
        }

        saveMutations(dirPath, savePath);
    }

    private void saveMutations(String dirPath, String savePath) {
        MultiMap<String, String> mutantList = FileBrowser.getMutations();
        int cont;
        String newPath;

        for(String key : mutantList.keySet()) {
            cont = 0;
            for(String mutant : mutantList.get(key)) {
                cont++;
                try {
                    newPath = "/ProjectMutant/" + key + "/Teste" + cont + "/";
                    FileUtils.copyDirectory(new File(dirPath), new File(savePath  + newPath));
                    FileCreator.saveFile(FileBrowser.getActualFile(), savePath  + newPath, mutant, "sv");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
