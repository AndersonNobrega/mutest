package org.lar.MutationSetup.MutationUtils;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.lar.FileUtils.FileBrowser;

import java.util.ArrayList;

public class TokenReplacer {
    private ArrayList<Integer> findCharIndex(BufferedTokenStream tokenStream, ParserRuleContext ctx, ArrayList<String> operators) {
        ArrayList<Integer> indexList = new ArrayList<>();

        for (Token token : tokenStream.getTokens(ctx.getStart().getTokenIndex(), ctx.getStop().getTokenIndex())) {
            if(operators.contains(token.getText())) {
                indexList.add(token.getTokenIndex());
            }
        }
        return indexList;
    }

    public void replaceTokens(BufferedTokenStream tokenStream, ParserRuleContext ctx, ArrayList<String> operators, String operatorLabel) {
        ArrayList<Integer> index = this.findCharIndex(tokenStream, ctx, operators);
        StringBuilder fileTemp;
        TokenStreamRewriter temp;

        for(int tokenIndex :  index) {
            for(String operator : operators) {
                temp = new TokenStreamRewriter(tokenStream);
                if(!tokenStream.get(tokenIndex).getText().equals(operator)) {
                    temp.replace(tokenIndex, operator);
                    fileTemp = new StringBuilder(temp.getText());
                    FileBrowser.appendToMutations(operatorLabel, fileTemp.toString());
                }
            }
        }
    }
}
