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

    public Attendance updateAttendance(Attendance updateAttendance) {
        Attendance oldAttendance = null;
        for (int i = 0; i < attendanceList.size(); i++) {
            if(updateAttendance.equals(attendanceList.get(i))) {
                oldAttendance = attendanceList.get(i);
                attendanceList.set(i, updateAttendance);
                attendanceStorage.saveAttendance(attendanceList);
                break;
            }
        }
        return oldAttendance;
    }

    public boolean hasRegisteredDate(String name) {
        Attendance attendance = new Attendance(name, LocalDate.now(), null);
        for (Attendance curAttendance : attendanceList) {
            if (curAttendance.hasRegisteredDate(attendance)) return true;
        }
        return false;
    }
}
