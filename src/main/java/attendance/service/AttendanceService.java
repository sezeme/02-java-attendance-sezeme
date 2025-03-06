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

    public boolean hasRegistered(Attendance attendance) {
        return attendanceRepository.hasRegisteredDate(attendance);
    }

    public void registerAttendance(Attendance attendance) {
        Attendance existingAttendance = attendanceRepository.selectAttendanceByCrewByTime(attendance);
        if (existingAttendance == null) {
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }

        if(hasRegistered(attendance)){
            throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
        }

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
