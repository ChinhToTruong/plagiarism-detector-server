package zev.plagiarismdetectorserver.exception;

public class UserInClassRoom extends RuntimeException {
    public UserInClassRoom(String message) {
        super(message);
    }
    public UserInClassRoom(){}
}
