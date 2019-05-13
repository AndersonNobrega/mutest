package org.lar.MutationSetup.Language.SystemVerilog;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.lar.LanguageUtils.SystemVerilog.SystemVerilogLexer;
import org.lar.LanguageUtils.SystemVerilog.SystemVerilogParser;
import org.lar.MutationSetup.Language.LanguageWalker;
import org.lar.MutationSetup.MutationOperators.Operator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.lar.FileUtils.FileBrowser.readFile;

public class SystemVerilogWalker implements LanguageWalker {

    private ArrayList<String> languageExtensionList = new ArrayList<String>() {
        {
            add("sv");
        }
    };

    /**
     * Pecorre a arvore gerada do codigo fonte
     *
     * @param file Arquivo a ser pecorrido
     * @param mutationOperator Operador de mutação a ser utilizado
     * @throws IOException Erro de leitura do arquivo
     */
    @Override
    public void walkFileTree(File file, Operator mutationOperator) throws IOException {
        String code = readFile(file);
        SystemVerilogLexer lexer = new SystemVerilogLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SystemVerilogParser parser = new SystemVerilogParser(tokens);
        ParserRuleContext tree = parser.source_text();
        ParseTreeWalker walker = new ParseTreeWalker();
        SystemVerilogListener extractor = new SystemVerilogListener(tokens, mutationOperator);
        walker.walk(extractor, tree);
    }

    /**
     * Diz qual formato de arquivo da linguagem
     *
     * @return Formato do arquivo
     */
    @Override
    public ArrayList<String> languageFormat() {
        return this.languageExtensionList;
    }
}
