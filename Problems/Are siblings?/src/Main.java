import java.io.File;

class Siblings {

    public static boolean areSiblings(File f1, File f2) {
        // implement me
        String file1 = f1.getParent();
        String file2 = f2.getParent();
        if (file1.equals(file2)) {
            return true;
        } else {
            return false;
        }
    }
}