package attendance.ui;


import attendance.service.utli.InputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;

public class InputView {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public char getChoice() throws IOException {
        // TODO : 입력 잘못했을 시 예외 던지는 기능 구현
        return br.readLine().charAt(0);
    }

    public String getNickName() throws IOException {
        return br.readLine();
    }

    public LocalTime getAttendanceTime() throws IOException {
        String[] input = br.readLine().split(":");
        LocalTime time = LocalTime.of(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        InputValidator.checkIsOpened(time);
        return time;
    }

    public LocalDate getDateOfMonth() throws IOException {
        int input = Integer.parseInt(br.readLine());
        LocalDate date = LocalDate.of(2025, 2, input);
        InputValidator.checkIsOpened(date);
        return date;
    }
}
