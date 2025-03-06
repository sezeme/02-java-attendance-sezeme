package attendance.service;

import attendance.domain.Attendance;
import attendance.persistence.AttendanceRepository;

import java.util.List;

public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<Attendance> findAttendance() {
        return attendanceRepository.selectAttendance();
    }

    public List<Attendance> findAttendanceByName(String name) {
        return attendanceRepository.selectAttendanceByCrew(name);
    }

    public boolean hasRegistered(String name) {
        return attendanceRepository.hasRegisteredDate(name);
    }

    public void registerAttendance(Attendance attendance) {
        attendanceRepository.insertAttendance(attendance);
    }

    public void modifyAttendance(Attendance updateAttendance) {
        Attendance existingAttendance = attendanceRepository.selectAttendanceByCrewByTime(updateAttendance);
        if (existingAttendance == null) {
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }

        attendanceRepository.updateAttendance(updateAttendance);
    }
}
