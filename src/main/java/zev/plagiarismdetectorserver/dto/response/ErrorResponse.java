package zev.plagiarismdetectorserver.dto.response;

import lombok.Setter;


@Setter
public class ErrorResponse extends ResponseData{
    private int statusCode = 400;
    private String message;
    public ErrorResponse(int statusCode, String[] messages) {
        super(statusCode, messages);
    }
    public ErrorResponse(int statusCode, String message) {
        super(statusCode, message);
    }

}
