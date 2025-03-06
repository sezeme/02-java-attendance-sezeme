package attendance.service.utli;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Formatter {
    public static String getDate(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM월 dd일 EEEE", Locale.KOREAN);
        return date.format(dtf);
    }

    public static String getTime(LocalTime time) {
        if(time == null) return "--:--";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm", Locale.KOREAN);
        return time.format(dtf);
    }
}
