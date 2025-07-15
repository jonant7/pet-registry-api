package com.example.petregistry.shared;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Range(Double min, Double max) {
    private static final Pattern PATTERN =
            Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*-\\s*(\\d+(?:\\.\\d+)?)?");

    public static Range parse(String text) {
        if (Objects.isNull(text)) {
            return new Range(null, null);
        }
        Matcher m = PATTERN.matcher(text.trim());
        if (!m.matches()) {
            return new Range(null, null);
        }
        double min = Double.parseDouble(m.group(1));

        double max = Objects.nonNull(m.group(2))
                ? Double.parseDouble(m.group(2))
                : min;
        return new Range(min, max);
    }
}
