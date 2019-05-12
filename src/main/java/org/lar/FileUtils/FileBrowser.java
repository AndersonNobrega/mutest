package org.lar.FileUtils;

import org.apache.commons.io.FilenameUtils;
import org.lar.MutationSetup.MutationOperators.Operator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class FileBrowser {
    private static String actualFile = "";
    private static MultiMap<String, String> mutations = new MultiMap<>();

    /**
     * Retorna o nome do arquivo
     *
     * @return Nome do arquivo
     */
    public static String getActualFile() {
        return FileBrowser.actualFile;
    }

    /**
     * Le um arquivo qualquer
     *
     * @param file     Arquivo para ler
     * @return O string do arquivo
     * @throws IOException Erro de leitura de arquivo
     */
    private static String readFile(File file) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, Charset.forName("UTF-8"));
    }

    /**
     * Pecorre o diretorio procurando por arquivos no formato desejado
     *
     * @param directory Diretorio que deseja pecorrer
     * @throws IOException Erro de leitura de arquivo
     */
    public static void browseDirectory(File directory, Operator mutationOperator) throws IOException {
        for (File child : directory.listFiles()) {
            if (child.isDirectory()) {
                browseDirectory(child, mutationOperator);
            } else {
                if (child.getName().endsWith(".sv")) {
                    FileBrowser.actualFile = FilenameUtils.getBaseName(child.getName());
                    mutationOperator.createMutants(readFile(child));
                }
            }
        }
    }

    public static MultiMap<String, String> getMutations() {
        return FileBrowser.mutations;
    }

    public static void appendToMutations(String operator, String mutant) {
        FileBrowser.mutations.map(operator, mutant);
    }
}
