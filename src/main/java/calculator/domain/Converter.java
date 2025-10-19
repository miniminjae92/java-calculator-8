package calculator.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public List<Integer> convertPositiveNumbers(List<String> payLoad) {
        if (payLoad.isEmpty()) {
            return List.of(0);
        }
        return payLoad.stream()
                .map(this::parseToken)
                .map(this::validatePositive)
                .collect(Collectors.toList());
    }

    private int parseToken(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 숫자입니다: " + token);
        }
    }

    private int validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수: " + number + "는 허용되지 않습니다");
        }
        return number;
    }
}
