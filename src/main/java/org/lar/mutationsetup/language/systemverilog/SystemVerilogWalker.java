package org.lar.mutationsetup.language.systemverilog;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.lar.languageutils.systemverilog.SystemVerilogLexer;
import org.lar.languageutils.systemverilog.SystemVerilogParser;
import org.lar.mutationsetup.language.LanguageWalker;
import org.lar.mutationsetup.operators.Operator;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.lar.mutationsetup.utils.files.FileBrowser.readFile;

public class SystemVerilogWalker implements LanguageWalker {

    private static final List<String> LANGUAGE_EXTENSIONS = Collections.singletonList("sv");

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
        SystemVerilogLexer lexer = new SystemVerilogLexer(CharStreams.fromString(code));
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
    public List<String> languageFormat() {
        return LANGUAGE_EXTENSIONS;
    }
}
