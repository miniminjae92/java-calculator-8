package calculator.domain;

import java.util.List;

public class Calculator {

    public long sum(List<Integer> numbers) {
        return numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
    }
}
