package attendance.service.utli;

import java.time.LocalDate;
import java.time.LocalTime;

public class InputValidator {
    public static void checkIsOpened(LocalDate date) {
        boolean isError = switch (date.getDayOfWeek()){
            case SATURDAY, SUNDAY -> true;
            default -> false;
        };
        if(isError) throw new IllegalStateException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
    }

    public static void checkIsOpened(LocalTime time) {
        boolean isError = time.isAfter(LocalTime.of(23, 0)) || time.isBefore(LocalTime.of(8, 0));
        if(isError) throw new IllegalStateException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
    }
}
