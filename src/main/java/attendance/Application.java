package attendance;

import attendance.service.AttendanceProgram;
import attendance.ui.InputView;
import attendance.ui.outputView.MainPrinter;

public class Application {
    public static void main(String[] args) {
        AttendanceProgram attendanceProgram = new AttendanceProgram(new MainPrinter(), new InputView());
        attendanceProgram.run();
    }
}
