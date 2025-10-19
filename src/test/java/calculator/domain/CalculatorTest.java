package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void 숫자리스트를_덧셈계산한다() {
        Calculator calculator = new Calculator();

        List<Integer> input = List.of(1,2,3);
        List<Integer> input2 = List.of(0);
        long result = calculator.sum(input);
        long result2 = calculator.sum(input2);

        assertThat(result).isEqualTo(6);
        assertThat(result2).isEqualTo(0);
    }

}
