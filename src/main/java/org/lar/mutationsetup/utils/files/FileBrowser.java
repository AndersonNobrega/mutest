package org.lar.mutationsetup.utils.files;

import org.antlr.v4.runtime.misc.MultiMap;
import org.apache.commons.io.FilenameUtils;
import org.lar.mutationsetup.language.LanguageWalker;
import org.lar.mutationsetup.operators.Operator;
import org.lar.mutationsetup.utils.ConstantsUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

public class FileBrowser {

    private FileBrowser() {
        throw new IllegalStateException(ConstantsUtil.CLASS_NOT_INSTANTIABLE);
    }

    private static String actualFile = "";

    private static final MultiMap<String, MultiMap<String, String>> mutations = new MultiMap<>();

    /**
     * Retorna o nome do arquivo
     *
     * @return Nome do arquivo
     */
    private static String getActualFile() {
        return FileBrowser.actualFile;
    }

    /**
     * Le um arquivo qualquer
     *
     * @param file     Arquivo para ler
     * @return O string do arquivo
     * @throws IOException Erro de leitura de arquivo
     */
    public static String readFile(File file) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, StandardCharsets.UTF_8);
    }

    /**
     * Pecorre o diretorio procurando por arquivos no formato desejado
     *
     * @param directory Diretorio que deseja pecorrer
     * @throws IOException Erro de leitura de arquivo
     */
    public static void browseDirectory(File directory, Operator mutationOperator, LanguageWalker walker) throws IOException {
        for (File child : Objects.requireNonNull(directory.listFiles())) {
            if (child.isDirectory()) {
                browseDirectory(child, mutationOperator, walker);
            } else {
                if (walker.languageFormat().contains(FilenameUtils.getExtension(child.getName())) &&
                        !FilenameUtils.getBaseName(child.getName()).contains("_testbench")) {
                    FileBrowser.actualFile = FilenameUtils.getBaseName(child.getName());
                    walker.walkFileTree(child, mutationOperator);
                }
            }
        }
    }

    public static MultiMap<String, MultiMap<String, String>> getMutations() {
        return FileBrowser.mutations;
    }

    public static void appendToMutations(String operator, String mutant) {
        MultiMap<String, String> fileMutants = new MultiMap<>();
        fileMutants.map(FileBrowser.getActualFile(), mutant);
        FileBrowser.mutations.map(operator, fileMutants);
    }
}
