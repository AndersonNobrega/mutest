package org.lar.MutationSetup.MutationOperators;

import org.lar.FileUtils.FileBrowser;
import org.lar.MutationSetup.MutationUtils.StringReplacer;

import java.util.ArrayList;

public class ArithmeticOperator implements Operator {

    private char[] arithmeticOperators = {'+', '-'};
    private StringReplacer stringReplacer = new StringReplacer();

    @Override
    public void createMutants(String file) {
        ArrayList<Integer> index = this.stringReplacer.findCharIndex(file, this.arithmeticOperators);
        StringBuilder fileTemp;

        for(int charIndex : index) {
            for(char arithmeticOperator : arithmeticOperators) {
                if(file.charAt(charIndex) != arithmeticOperator) {
                    fileTemp = new StringBuilder(file);
                    fileTemp.setCharAt(charIndex, arithmeticOperator);
                    FileBrowser.appendToMutations("AOR", fileTemp.toString());
                }
            }
        }
    }
}
