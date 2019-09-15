public final class ExecError extends RuntimeException {
    public ExecError() {
    }

    public ExecError(String message, int exitCode) {
        super(message);
        //this.exitCode = exitCode;
    }
}