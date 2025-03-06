package attendance.service;

public class ValidChecker {
    public static void isRegistered(boolean empty) {
        throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
    }
}
