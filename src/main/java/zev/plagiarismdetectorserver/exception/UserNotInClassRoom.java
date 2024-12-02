package zev.plagiarismdetectorserver.exception;

public class UserNotInClassRoom extends RuntimeException {
    public UserNotInClassRoom(String message) {
        super(message);
    }
    public UserNotInClassRoom(){}
}
