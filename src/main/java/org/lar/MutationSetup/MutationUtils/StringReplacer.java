package org.lar.MutationSetup.MutationUtils;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class StringReplacer {
    public ArrayList<Integer> findCharIndex(BufferedTokenStream tokenStream, ParserRuleContext ctx, ArrayList<String> operators) {
        ArrayList<Integer> indexList = new ArrayList<>();

        for (Token token : tokenStream.getTokens(ctx.getStart().getTokenIndex(), ctx.getStop().getTokenIndex())) {
            //System.out.println(token.getType()); //150 73
            if(operators.contains(token.getText())) {
                indexList.add(token.getTokenIndex());
            }
        }
        return indexList;
    }
}
