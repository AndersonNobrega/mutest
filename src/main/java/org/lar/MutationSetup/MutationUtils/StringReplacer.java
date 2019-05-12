package org.lar.MutationSetup.MutationUtils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class StringReplacer {
    public ArrayList<Integer> findCharIndex(String fileContent, char[] operators) {
        ArrayList<Integer> indexList = new ArrayList<>();

        CharacterIterator charIterator = new StringCharacterIterator(fileContent);
        for (char ch = charIterator.first(); ch != CharacterIterator.DONE; ch = charIterator.next()) {
            if(contains(ch, operators)) {
                indexList.add(charIterator.getIndex());
            }
        }
        return indexList;
    }

    private boolean contains(char character, char[] array) {
        for (char ch : array) {
            if (ch == character) {
                return true;
            }
        }
        return false;
    }
}
