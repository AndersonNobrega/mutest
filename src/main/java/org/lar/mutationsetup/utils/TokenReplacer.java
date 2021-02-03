package org.lar.mutationsetup.utils;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.lar.mutationsetup.utils.files.FileBrowser;

import java.util.ArrayList;
import java.util.List;

public class TokenReplacer {
    private List<Integer> findCharIndex(BufferedTokenStream tokenStream, ParserRuleContext ctx, List<String> operators) {
        List<Integer> indexList = new ArrayList<>();

        for (Token token : tokenStream.getTokens(ctx.getStart().getTokenIndex(), ctx.getStop().getTokenIndex())) {
            if(operators.contains(token.getText())) {
                indexList.add(token.getTokenIndex());
            }
        }
        return indexList;
    }

    public void replaceTokens(BufferedTokenStream tokenStream, ParserRuleContext ctx, List<String> operators, String operatorLabel) {
        List<Integer> index = this.findCharIndex(tokenStream, ctx, operators);
        StringBuilder fileContent;
        TokenStreamRewriter tokenStreamRewriter;

        for (int tokenIndex : index) {
            for (String operator : operators) {
                tokenStreamRewriter = new TokenStreamRewriter(tokenStream);
                if (!tokenStream.get(tokenIndex).getText().equals(operator)) {
                    tokenStreamRewriter.replace(tokenIndex, operator);
                    fileContent = new StringBuilder(tokenStreamRewriter.getText());
                    FileBrowser.appendToMutations(operatorLabel, fileContent.toString());
                }
            }
        }
    }
}
