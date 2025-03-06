package attendance.ui.outputView;

import java.time.LocalDate;

public class MainPrinter {
    public void displayMenu() {
        System.out.println(
                "오늘은 "+ LocalDate.now() +"입니다. 기능을 선택해 주세요.\n" +
                        "1. 출석 확인\n" +
                        "2. 출석 수정\n" +
                        "3. 크루별 출석 기록 확인\n" +
                        "4. 제적 위험자 확인\n" +
                        "Q. 종료"
        );
    }

    public void displayToShowAttendanceLog(String name) {
        System.out.println("이번 달 " + name + "의 출석 기록입니다.\n");
    }
}
