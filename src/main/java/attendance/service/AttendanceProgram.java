package attendance.service;

import attendance.domain.Attendance;
import attendance.domain.AttendanceLog;
import attendance.persistence.AttendanceRepository;
import attendance.persistence.FileAttendanceStorage;
import attendance.service.utli.Formatter;
import attendance.service.utli.ValidChecker;
import attendance.ui.InputView;
import attendance.ui.outputView.AttendancePrinter;
import attendance.ui.outputView.AttendanceRegiserPrinter;
import attendance.ui.outputView.AttendanceUpdatePrinter;
import attendance.ui.outputView.MainPrinter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceProgram {
    private final AttendanceService attendanceService;
    private final AttendanceLogService attendanceLogService;
    private final MainPrinter mainPrinter;
    private final InputView inputView;

    public AttendanceProgram(MainPrinter mainPrinter, InputView inputView) {
        AttendanceRepository attendanceRepository = new AttendanceRepository(new FileAttendanceStorage());
        this.attendanceService = new AttendanceService(attendanceRepository);
        AttendanceLogRepository attendanceLogRepository = new AttendanceLogRepository(attendanceService.findAttendance());
        this.attendanceLogService = new AttendanceLogService(attendanceLogRepository);
        this.mainPrinter = mainPrinter;
        this.inputView = inputView;
    }

    public void run() {
        while (true) {
            mainPrinter.displayMenu(Formatter.getDate(LocalDate.now()));

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
        AttendanceRegiserPrinter printer = new AttendanceRegiserPrinter();
        printer.displayToGetName();
        String name = inputView.getNickName();

        ValidChecker.isRegistered(attendanceService.findAttendanceByName(name).isEmpty());
        ValidChecker.isDuplicatedDate(attendanceService.hasRegistered(name));

        printer.displayToGetAttendanceTime();
        Attendance attendance = new Attendance(name, LocalDate.now(), inputView.getAttendanceTime());
        attendanceService.registerAttendance(attendance);
        mainPrinter.displayAttandence(attendance.toString());
    }

    private void updateAttendance() throws IOException {
        AttendanceUpdatePrinter printer = new AttendanceUpdatePrinter();
        printer.displayToGetName();
        String name = inputView.getNickName();
        printer.displayToGetAttendanceDate();
        LocalDate date = inputView.getDateOfMonth();
        printer.displayToGetAttendanceTime();
        LocalTime time = inputView.getAttendanceTime();
        Attendance attendance = new Attendance(name, date, time);
        Attendance oldAttendance = attendanceService.modifyAttendance(attendance);

        mainPrinter.displayAttandence(oldAttendance + " -> " + attendance + " 수정 완료!");
    }

    private void checkAttendanceLogByCrew() throws IOException {
        AttendancePrinter printer = new AttendancePrinter();
        printer.displayToGetName();
        String name = inputView.getNickName();
        AttendanceLog log = new AttendanceLog(attendanceService.findAttendanceByName(name));
        mainPrinter.displayAttandence(log.getInformation());
    }

    private void checkHasExpulsionRisk() {

    }


}
