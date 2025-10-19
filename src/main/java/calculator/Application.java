package calculator;

import calculator.domain.Calculator;
import calculator.domain.Converter;
import calculator.domain.Delimiter;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        String input = inputView.getString();

        Delimiter delimiter = new Delimiter();
        List<String> seperated = delimiter.seperateString(input);

        Converter converter = new Converter();
        List<Integer> numbers = converter.convertPositiveNumbers(seperated);

        Calculator calculator = new Calculator();
        OutputView outputView = new OutputView();
        outputView.printResult(calculator.sum(numbers));
    }
}
