package org.lar;

import org.lar.mutationsetup.InitApp;
import org.lar.mutationsetup.utils.ConstantsUtil;

import java.util.logging.Logger;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger( Runner.class.getName() );

    /**
     * Classe principal do projeto
     *
     * @param args Deve ter a lista de argumentos para o extrator
     */
    public static void main(String[] args) {
        InitApp initApp = new InitApp();
        String operatorsOption = "";
        String projectPath = "";
        String savePath = "";

        try {
            if (args.length == 0) {
                LOGGER.info(ConstantsUtil.MANUAL);
                System.exit(0);
            }

            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-opr":
                        operatorsOption = args[++i];
                        break;
                    case "-d":
                        projectPath = args[++i];
                        break;
                    case "-f":
                        savePath = args[++i];
                        break;
                    case "-help":
                    default:
                        LOGGER.info(ConstantsUtil.MANUAL);
                        System.exit(0);
                        break;
                }
            }

            LOGGER.info("Running...");
            initApp.start(operatorsOption.split(","), projectPath, savePath);
            LOGGER.info("Done.");
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info(ConstantsUtil.MANUAL);
        }
    }
}
