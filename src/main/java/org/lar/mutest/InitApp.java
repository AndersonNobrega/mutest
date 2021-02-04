package org.lar.mutest;

import org.antlr.v4.runtime.misc.MultiMap;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.commons.io.FileUtils;
import org.lar.mutest.utils.ConstantsUtil;
import org.lar.mutest.utils.files.FileBrowser;
import org.lar.mutest.utils.files.FileCreator;
import org.lar.mutest.language.LanguageWalker;
import org.lar.mutest.operators.Operator;
import org.lar.mutest.utils.performance.MemoryRuntime;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class InitApp {

    private static final Logger LOGGER = Logger.getLogger( InitApp.class.getName() );

    public void start(String[] operators, String dirPath, String savePath) {
        long start = System.currentTimeMillis();

        for (String operator : operators) {
            Operator mutationOperator = OperatorFactory.getOperator(operator);
            LanguageWalker walker = LanguageWalkerFactory.getWalker("SV");
            try {
                FileBrowser.browseDirectory(new File(dirPath), mutationOperator, walker);
            } catch (IOException error) {
                LOGGER.warning(ConstantsUtil.PATH_NOT_FOUND);
            }
        }

        saveMutations(dirPath, savePath);

        if (AppOptions.isMemoryRuntimeEnabled()) {
            MemoryRuntime memoryRuntime = new MemoryRuntime();
            memoryRuntime.saveInfo(start, savePath);
        }
    }

    private void saveMutations(String dirPath, String savePath) {
        MultiMap<String, MultiMap<String, String>> mutantList = FileBrowser.getMutations();
        int cont;
        String newPath;

        if(mutantList.isEmpty()) {
            LOGGER.info(ConstantsUtil.NO_MUTANTS_CREATED);
        } else {
            for (String key : mutantList.keySet()) {
                cont = 0;
                for (MultiMap<String, String> fileMap : mutantList.get(key)) {
                    for (Pair<String, String> fileMutant : fileMap.getPairs()) {
                        cont++;
                        try {
                            String file = fileMutant.a;
                            String mutant = fileMutant.b;
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
