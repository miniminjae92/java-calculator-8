package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ConverterTest {

    private final Converter converter = new Converter();

    private static Stream<Arguments> nomalConversionProvider() {
        return Stream.of(
                Arguments.of(List.of("1", "2", "3"), List.of(1, 2, 3)),
                Arguments.of(List.of("5", "10"), List.of(5, 10)),
                Arguments.of(List.of("7"), List.of(7)),
                Arguments.of(List.of("0", "100"), List.of(0, 100)),
                Arguments.of(List.of("2147483647", "1"), List.of(2147483647, 1))
        );
    }

    @ParameterizedTest
    @MethodSource("nomalConversionProvider")
    void 정상적인_변환_테스트(List<String> input, List<Integer> expected) {
        List<Integer> result = converter.convertPositiveNumbers(input);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 숫자가_아닐경우_예외를_반환한다() {
        List<String> invalid = List.of("1", "a", "2");

        assertThatThrownBy(() -> {
            converter.convertPositiveNumbers(invalid);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 빈리스트일_경우_리스트0을_반환한다() {
        List<String> empty = List.of();
        List<Integer> result = converter.convertPositiveNumbers(empty);
        assertThat(result).isEqualTo(List.of(0));
    }

    @Test
    void 음수일경우_예외를_반환한다() {
        List<String> negative = List.of("2", "-1", "3", "-2147483648");

        assertThatThrownBy(() -> {
            converter.convertPositiveNumbers(negative);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 한계값_테스트() {
        List<String> t1 = List.of("2147483648");
        List<String> t2 = List.of("-2147483649");
        assertThatThrownBy(() -> {
            converter.convertPositiveNumbers(t1);
        }).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> {
            converter.convertPositiveNumbers(t2);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
