package org.lar.MutationSetup.MutationOperators;

import org.lar.FileUtils.FileBrowser;

public class RelationalOperator implements Operator {
    @Override
    public void createMutants(String file) {
        String[] relationalOperators = {">", ">=", "<", "<=", "==", "!="};

        for(int i = 0; i < 4; i++) {
            FileBrowser.appendToMutations("ROR", "");
        }
    }
}
