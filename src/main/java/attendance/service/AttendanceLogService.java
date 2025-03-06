package attendance.service;

import attendance.persistence.AttendanceLogRepository;

public class AttendanceLogService {
    private final AttendanceLogRepository attendanceLogRepository;

    public AttendanceLogService(AttendanceLogRepository attendanceLogRepository) {
        this.attendanceLogRepository = attendanceLogRepository;
    }

    public String getInformationOfRiskOfWeeding() {
        return attendanceLogRepository.findRiskOfWeeding();
    }
}
