package attendance.ui.outputView;

public class AttendanceUpdatePrinter extends AttendancePrinter {
    public AttendanceUpdatePrinter() {
        setText("출석을 수정하려는 크루의 ");
    }

    public void displayToGetAttendanceDate() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
    }

    public void displayToGetAttendanceTime() {
        System.out.println("언제로 변경하겠습니까?");
    }
}
