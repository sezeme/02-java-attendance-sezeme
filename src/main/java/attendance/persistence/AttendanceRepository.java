package attendance.persistence;

import attendance.domain.Attendance;

import java.time.LocalDate;
import java.util.List;

public class AttendanceRepository {
    private final AttendanceStorage attendanceStorage;
    private final List<Attendance> attendanceList;

    public AttendanceRepository(AttendanceStorage attendanceStorage) {
        this.attendanceStorage = attendanceStorage;
        this.attendanceList = attendanceStorage.loadAttendance();
    }

    public List<Attendance> selectAttendance() {
        return attendanceList;
    }

    public List<Attendance> selectAttendanceByCrew(String name) {
        return attendanceList.stream().filter(user -> user.getNickname().equals(name)).toList();
    }

    public Attendance selectAttendanceByCrewByTime(Attendance attendance) {
        return attendanceList.stream().filter(user -> user.equals(attendance)).findFirst().orElse(null);
    }

    public void insertAttendance(Attendance attendance) {
        attendanceList.add(attendance);
        attendanceStorage.saveAttendance(attendanceList);
    }

    public void updateAttendance(Attendance updateAttendance) {
        for (int i = 0; i < attendanceList.size(); i++) {
            if (attendanceList.get(i).getNickname().equals(updateAttendance.getNickname())) {
                attendanceList.set(i, updateAttendance);
                attendanceStorage.saveAttendance(attendanceList);
                break;
            }
        }
    }

    public boolean hasRegisteredDate(String name) {
        Attendance attendance = new Attendance(name, LocalDate.now(), null);
        for (Attendance curAttendance : attendanceList) {
            if (curAttendance.hasRegisteredDate(attendance)) return true;
        }
        return false;
    }
}
