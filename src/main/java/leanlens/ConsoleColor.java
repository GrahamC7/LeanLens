package leanlens;

public enum ConsoleColor {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    BOLD("\u001B[1m");

    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
