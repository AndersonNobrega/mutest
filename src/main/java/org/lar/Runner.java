package org.lar;

import org.lar.MutationSetup.Init;

public class Runner {
    /**
     * Classe principal do projeto
     *
     * @param args Deve ter a lista de argumentos para o extrator
     */
    public static void main(String[] args) {
        Init init = new Init();
        final String MANUAL = "You must set the following options:"
                + "\n\t-opr: operator(s) that will be used"
                + "\n\t-d: the project path must be inserted after this option"
                + "\n\t-f: path to save the resulting file(s)"
                + "\n\n\tEXAMPLE: -opr AOR -d ~/SomeProject/ -f ~/Downloads";
        String operatorsOption = "", projectPath = "", savePath = "";

        try {
            if (args.length == 0) {
                System.out.println(MANUAL);
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
                        System.out.println(MANUAL);
                        System.exit(0);
                        break;
                }
            }

            System.out.println("Running...");
            init.start(operatorsOption.split(","), projectPath, savePath);
            System.out.println("Done.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MANUAL);
        }
    }
}
