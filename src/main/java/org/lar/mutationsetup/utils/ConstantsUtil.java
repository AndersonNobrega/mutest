package org.lar.mutationsetup.utils;

public class ConstantsUtil {

    private ConstantsUtil() {
        throw new IllegalStateException(ConstantsUtil.CLASS_NOT_INSTANTIABLE);
    }

    public static final String CLASS_NOT_INSTANTIABLE = "Class not instantiable";

    public static final String MANUAL = "You must set the following options:"
            + "\n\t-opr: operator(s) that will be used"
            + "\n\t-d: the project path must be inserted after this option"
            + "\n\t-f: path to save the resulting file(s)"
            + "\n\t-stat: show memory used"
            + "\n\n\tEXAMPLE: -opr AOR -d ~/SomeProject/ -f ~/Downloads";

    public static final String PATH_NOT_FOUND = "Path not found";

    public static final String NO_MUTANTS_CREATED = "No mutants were created";

    public static final String DIR_NOT_CREATED = "Directory not created";
}
