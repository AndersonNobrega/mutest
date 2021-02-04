package org.lar.mutest.language;

import org.lar.mutest.operators.Operator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface LanguageWalker {

    /**
     * Pecorre a arvore gerada do codigo fonte
     *
     * @param file Arquivo a ser pecorrido
     * @param mutationOperator Operador de mutação a ser utilizado
     * @throws IOException Erro de leitura do arquivo
     */
    void walkFileTree(File file, Operator mutationOperator) throws IOException;

    /**
     * Diz qual formato de arquivo da linguagem
     *
     * @return Formato do arquivo
     */
    List<String> languageFormat();
}