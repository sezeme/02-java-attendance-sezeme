package attendance.persistence;

import attendance.domain.Attendance;
import attendance.domain.AttendanceLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AttendanceLogRepository {
    private List<AttendanceLog> attendanceLogList;

    public AttendanceLogRepository(List<Attendance> attendances) {
        Map<String, List<Attendance>> attendanceMap = attendances.stream().collect(Collectors.groupingBy(Attendance::getNickname));
        List<String> keySet = new ArrayList<>(attendanceMap.keySet());
        attendanceLogList = new ArrayList<>();
        for(String key : keySet) {
            attendanceLogList.add(new AttendanceLog(attendanceMap.get(key)));
        }
    }

    public String findRiskOfWeeding() {
        StringBuilder sb = new StringBuilder("제적 위험자 조회 결과\n");
        attendanceLogList.stream().filter( a -> a.getManageState() != null )
                .sorted()
                .forEach(i -> sb.append(i).append("\n"));
        return sb.toString();
    }
}
