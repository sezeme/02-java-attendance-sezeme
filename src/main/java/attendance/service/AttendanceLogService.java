package attendance.service;

import attendance.domain.Attendance;
import attendance.persistence.AttendanceRepository;

import java.util.List;

public class AttendanceLogService {
    private final AttendanceLogRepository attendanceLogRepository;

    public AttendanceLogService(AttendanceLogRepository attendanceLogRepository) {
        this.attendanceLogRepository = attendanceLogRepository;
    }
}
