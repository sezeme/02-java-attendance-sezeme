package attendance.ui.outputView;

public abstract class AttendancePrinter {
    public static final String GET_NAME_TEXT = "닉네임을 입력해주세요.";
    private String text;
    public void displayToGetName(){
        System.out.println(text + GET_NAME_TEXT);
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract void displayToGetAttendanceTime();
}
