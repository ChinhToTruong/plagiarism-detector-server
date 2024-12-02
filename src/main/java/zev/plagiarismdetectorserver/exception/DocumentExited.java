package zev.plagiarismdetectorserver.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DocumentExited extends RuntimeException {
    public DocumentExited(String message) {
        super(message);
    }
}
