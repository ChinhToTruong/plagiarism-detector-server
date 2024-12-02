package zev.plagiarismdetectorserver.exception;

public class DocumentNotFound extends RuntimeException {
    public DocumentNotFound(String message) {
        super(message);
    }
    public DocumentNotFound() {}
}
