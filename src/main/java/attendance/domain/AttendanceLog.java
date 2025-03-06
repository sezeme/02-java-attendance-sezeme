package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AttendanceLog {
    int attendCnt;
    int lateCnt;
    int absentCnt;
    ManageState manageState;
    String name;
    List<Attendance> attendanceList;

    public AttendanceLog(List<Attendance> attendanceList) {
        this.name = attendanceList.get(0).getNickname();
        this.attendanceList = fillAllDates(attendanceList);
        Collections.sort(this.attendanceList); // Comparable 구현을 활용한 정렬
        calcAttendanceState();
        calcManageState();
    }

    private List<Attendance> fillAllDates(List<Attendance> existingAttendances) {
        LocalDate startDate = LocalDate.of(2025, 2, 20);
        LocalDate today = LocalDate.now();
        Set<LocalDate> existingDates = existingAttendances.stream()
                .map(Attendance::getDate)
                .collect(Collectors.toSet());

        List<Attendance> fullAttendanceList = new ArrayList<>();

        while (!startDate.isAfter(today)) {
            if (!startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) && !startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                    && !startDate.equals(LocalDate.of(2025, 3, 3))) {
                if (existingDates.contains(startDate)) {
                    // 기존 출석 기록이 있는 경우 그대로 추가
                    fullAttendanceList.add(getAttendanceByDate(existingAttendances, startDate));
                } else {
                    // 출석 기록이 없는 경우 time을 null로 설정하여 추가
                    fullAttendanceList.add(new Attendance(name, startDate, null));
                }
            }
            startDate = startDate.plusDays(1);
        }
        return fullAttendanceList;
    }

    private Attendance getAttendanceByDate(List<Attendance> attendances, LocalDate date) {
        return attendances.stream()
                .filter(a -> a.getDate().equals(date))
                .findFirst()
                .orElse(null);
    }

    private void calcAttendanceState() {
        attendCnt = 0;
        lateCnt = 0;
        absentCnt = 0;
        for (Attendance atd : attendanceList) {
            switch (atd.getState()) {
                case 출석 -> attendCnt++;
                case 지각 -> lateCnt++;
                case 결석 -> absentCnt++;
            }
        }
    }

    private void calcManageState() {
        int newAbsentDate = absentCnt + lateCnt/3;
        if (newAbsentDate > 5)
            manageState = ManageState.제적;
        else if (newAbsentDate >= 3)
            manageState = ManageState.면담;
        else if (newAbsentDate >= 2)
            manageState = ManageState.경고;
    }

    public String getInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append("이번 달 ").append(name).append("의 출석 기록입니다.\n\n");

        for (Attendance atd : attendanceList) {
            sb.append(atd).append("\n");
        }

        calcAttendanceState();

        sb.append("\n");
        sb.append("출석: ").append(attendCnt).append("회\n");
        sb.append("지각: ").append(lateCnt).append("회\n");
        sb.append("결석: ").append(absentCnt).append("회\n");

        switch (manageState) {
            case 제적 -> sb.append("\n제적 대상자입니다.\n");
            case 면담 -> sb.append("\n면담 대상자입니다.\n");
            case 경고 -> sb.append("\n경고 대상자입니다.\n");
        }

        return sb.toString();
    }
}

