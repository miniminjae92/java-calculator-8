package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DelimiterTest {
    @Test
    void 빈공백이_들어오는경우_빈문자열을_반환한다() {
        String input = "";
        Delimiter delimiter = new Delimiter();
        List<String> result = delimiter.seperateString(input);
        assertThat(result).isEqualTo(List.of(""));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2|1:2",
            "1,2,3|1:2:3",
            "1,2:3|1:2:3"
    }, delimiter = '|')
    void 쉼표가_포함된_문자열이_들어올경우_구분한다(String input, String expected) {
        Delimiter delimiter = new Delimiter();
        List<String> result = delimiter.seperateString(input);
        assertThat(result).isEqualTo(List.of(expected.split(":")));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'//;\\n1;2;3'|1:2:3",
            "'//;\\n1;2;3;'|1:2:3",
            "'//;\\n1;2,3:4;'|1:2:3:4",
            "'//abc\\n1abc2,3:4abc'|1:2:3:4",
    }, delimiter = '|')
    void 커스텀구분자가_있는경우_구분한다(String input, String expected) {
        Delimiter delimiter = new Delimiter();
        List<String> result = delimiter.seperateString(input);
        assertThat(result).isEqualTo(List.of(expected.split(":")));
    }
}
