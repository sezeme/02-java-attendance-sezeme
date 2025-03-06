package attendance.persistence;

import attendance.domain.Attendance;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileAttendanceStorage implements AttendanceStorage {
    private static final String FILE_PATH = "src/main/java/attendance/db/attendances.csv";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void saveAttendance(List<Attendance> attendanceList) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // 디렉토리 자동 생성

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            writer.write("nickname,date,time"); // 헤더 추가
            writer.newLine();

            for (Attendance attendance : attendanceList) {
                writer.write(attendance.getNickname() + "," +
                        attendance.getDate().format(DATE_FORMAT) + " " +
                        attendance.getTime().format(TIME_FORMAT));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생", e);
        }
    }

    @Override
    public List<Attendance> loadAttendance() {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) return new ArrayList<>();

        List<Attendance> attendanceList = new ArrayList<>();
        DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line = reader.readLine(); // 헤더 제거
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");  // 공백 제거
                if (parts.length != 2) continue; // 데이터 형식이 맞지 않으면 건너뛰기

                String nickname = parts[0];
                LocalDateTime dateTime = LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMAT);

                attendanceList.add(new Attendance(nickname, dateTime.toLocalDate(), dateTime.toLocalTime()));
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 로딩 중 오류 발생", e);
        }

        return attendanceList;
    }
}
