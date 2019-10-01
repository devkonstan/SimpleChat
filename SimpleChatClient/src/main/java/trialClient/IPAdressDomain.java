package trialClient;

public class IPAdressDomain {
    private String fullPath;
    private char pathSeparator;

    public IPAdressDomain(String str, char sep) {
        fullPath = str;
        pathSeparator = sep;
    }

    public String extension() {
        int slash = fullPath.lastIndexOf(pathSeparator);
        System.out.println(slash);
        return fullPath.substring(slash + 1);
    }
}
