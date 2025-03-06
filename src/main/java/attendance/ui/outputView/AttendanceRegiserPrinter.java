package attendance.ui.outputView;

public class AttendanceRegiserPrinter extends AttendancePrinter {
    public AttendanceRegiserPrinter() {
        setText("");
    }

    public void displayToGetAttendanceTime() {
        System.out.println("등교 시간을 입력해 주세요.");
    }
}