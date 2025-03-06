package attendance.domain;


import attendance.service.utli.Formatter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Attendance {
    private String nickname;
    private LocalDate date;
    private LocalTime time;

    public Attendance(String nickname, LocalDate date, LocalTime time) {
        this.nickname = nickname;
        this.date = date;
        this.time = time;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean hasRegisteredDate(Attendance a) {
        return nickname.equals(a.getNickname()) && date.equals(a.getDate());
    }

    public String getInformation() {
        return Formatter.getDate(date) + " " + Formatter.getTime(time);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Attendance atd = (Attendance) o;
        return nickname.equals(atd.getNickname()) && date.equals(atd.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, date, time);
    }

    @Override
    public String toString() {
        return nickname + "," + date + " " + time;
    }
}
