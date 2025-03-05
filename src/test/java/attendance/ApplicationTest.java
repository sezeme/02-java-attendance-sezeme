package attendance;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static camp.nextstep.edu.missionutils.test.Assertions.assertNowTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 잘못된_형식_예외_테스트() {
        assertNowTest(
            () -> assertThatThrownBy(() -> run("1", "짱수", "33:71"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 잘못된 형식을 입력하였습니다."),
            LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Test
    void 등록되지_않은_닉네임_예외_테스트() {
        assertNowTest(
            () -> assertThatThrownBy(() -> run("1", "빈봉"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 등록되지 않은 닉네임입니다."),
            LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Test
    void 주말_또는_공휴일_예외_테스트() {
        assertNowTest(
            () -> assertThatThrownBy(() -> run("1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 12월 14일 토요일은 등교일이 아닙니다."),
            LocalDate.of(2024, 12, 14).atStartOfDay()
        );
    }

    @Test
    void 출석_확인_기능_테스트() {
        assertNowTest(
            () -> {
                runException("1", "짱수", "08:00");
                assertThat(output()).contains("12월 13일 금요일 08:00 (출석)");
            },
            LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Test
    void 출석_수정_및_크루별_출석_기록_확인_기능_테스트() {
        assertNowTest(
            () -> {
                runException("2", "짱수", "12", "10:31", "3", "짱수");
                assertThat(output()).contains(
                    "12월 12일 목요일 10:00 (출석) -> 10:31 (결석) 수정 완료!",
                    "12월 02일 월요일 13:00 (출석)",
                    "12월 03일 화요일 10:00 (출석)",
                    "12월 04일 수요일 10:00 (출석)",
                    "12월 05일 목요일 10:00 (출석)",
                    "12월 06일 금요일 10:00 (출석)",
                    "12월 09일 월요일 13:00 (출석)",
                    "12월 10일 화요일 10:00 (출석)",
                    "12월 11일 수요일 --:-- (결석)",
                    "12월 12일 목요일 10:31 (결석)",
                    "출석: 7회",
                    "지각: 0회",
                    "결석: 2회",
                    "경고 대상자"
                );
            },
            LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
