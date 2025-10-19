package calculator.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Delimiter {

    private static final Set<String> DEFAULT_DELIMITERS = Set.of(",", ":");
    private static final String START_FLAG = "//";
    private static final String END_FLAG = "\\\\n";
    private static final String REGEX_CUSTOM = START_FLAG + "(.*?)" + END_FLAG + "(.*)";
    private static final Pattern CUSTOM_DELIMITER = Pattern.compile(REGEX_CUSTOM);
    private final Set<String> delimiters;

    public Delimiter() {
        this.delimiters = new HashSet<>(DEFAULT_DELIMITERS);
    }

    public List<String> seperateString(String string) {
        if (string == null) {
            throw new IllegalArgumentException("string is null");
        }
        String checked = checkCustomDelimiter(string);
        String regex = delimiters.stream().map(Pattern::quote).collect(Collectors.joining("|"));
        return List.of(checked.split(regex));
    }

    private String checkCustomDelimiter(String string) {
        Matcher matcher = CUSTOM_DELIMITER.matcher(string);
        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            String payLoad = matcher.group(2);
            if (customDelimiter.isEmpty()) {
                return payLoad;
            }
            delimiters.add(customDelimiter);
            return payLoad;
        }
        return string;
    }

}
