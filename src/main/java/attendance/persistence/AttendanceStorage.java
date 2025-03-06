package attendance.persistence;

import attendance.domain.Attendance;

import java.util.List;

/* 파일 저장 방식, 데이터베이스 저장 방식 등을 쉽게 교체할 수 있도록 인터페이스 분리 */
public interface AttendanceStorage {
    void saveAttendance(List<Attendance> users);
    List<Attendance> loadAttendance();
}
