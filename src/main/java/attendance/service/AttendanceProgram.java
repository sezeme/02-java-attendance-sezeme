package attendance.service;

import attendance.domain.Attendance;
import attendance.persistence.AttendanceRepository;
import attendance.persistence.FileAttendanceStorage;
import attendance.ui.InputView;
import attendance.ui.outputView.AttendancePrinter;
import attendance.ui.outputView.AttendanceRegiserPrinter;
import attendance.ui.outputView.MainPrinter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AttendanceProgram {
    private final AttendanceService attendanceService;
    private final MainPrinter mainPrinter;
    private final InputView inputView;
    AttendancePrinter printer;

    public AttendanceProgram(MainPrinter mainPrinter, InputView inputView) {
        AttendanceRepository attendanceRepository = new AttendanceRepository(new FileAttendanceStorage());
        this.attendanceService = new AttendanceService(attendanceRepository);
        this.mainPrinter = mainPrinter;
        this.inputView = inputView;
        this.printer = null;
    }

    public void run() {
        while (true) {
            mainPrinter.displayMenu();

            try {
                char choice = inputView.getChoice();
                switch (choice) {
                    case '1' -> registerAttendance();
                    case '2' -> updateAttendance();
                    case '3' -> checkAttendanceLogByCrew();
                    case '4' -> checkHasExpulsionRisk();
                    case 'Q' -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> System.out.println("[ERROR] 잘못된 입력입니다. 다시 선택해주세요.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void registerAttendance() throws IOException {
        printer = new AttendanceRegiserPrinter();
        printer.displayToGetName();
        String name = inputView.getNickName();

        ValidChecker.isRegistered(attendanceService.findAttendanceByName(name).isEmpty());
        ValidChecker.isDuplicatedDate(attendanceService.hasRegistered(name));

        printer.displayToGetAttendanceTime();
        Attendance attendance = new Attendance(name, LocalDate.now(), inputView.getAttendanceTime());
        attendanceService.registerAttendance(attendance);
        mainPrinter.displayAttandence(attendance.getInformation());
    }

    private void checkHasExpulsionRisk() {
    }

    private void checkAttendanceLogByCrew() {
        
    }

    private void updateAttendance() {
        
    }


}
