package attendance.service.utli;

public class ValidChecker {
    public static void isRegistered(boolean empty) {
        if(empty) throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
    }

    public static void isDuplicatedDate(boolean registered) {
        if(registered) throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
    }
}
