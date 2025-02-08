package org.docksidestage.bizfw.colorbox.parser;

import java.util.*; // Asteriskでインポートされてしまってるけど、一旦スルーで！

/**
 * @author shiny
 */
public class ColorBoxParser {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final String mapPrefix = "map:{";
    private static final String mapSuffix = "}";
    private static final String assignmentOperator = "=";
    private static final String delimiter = ";";

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    private ColorBoxParser() {}

    /**
     * Parses and converts the string in ColorBox's SecretBox-Content into java.utils.Map;
     * @param input The content (string) to be converted to map. (NotNull)
     * @throws IllegalArgumentException When the given input is invalid e.g. does not start with "map:{", is null, does not have delimiter, etc.
     * @return The parsed Result as Map
     */
    public static Map<String, Object> toMap(String input) {
        boolean isValidInput = validateInput(input);
        if (!isValidInput) {
            throw new IllegalArgumentException("Invalid input. "
                    + "The input string cannot be null and must start with \"map:{\" and ends with \"}\". Actual Input: "
                    + input
            );
        }

        if (input.isEmpty()) {
            return Collections.emptyMap();
        }

        String remaining = input;

        remaining = consumeWhiteSpace(remaining);
        remaining = consume(remaining, mapPrefix);
        remaining = consumeWhiteSpace(remaining);

        Map<String, Object> result = new LinkedHashMap<>(); // 順序を保持したいので、LinkedHashMapを使っている
        while (!remaining.isEmpty()) {
            remaining = consumeWhiteSpace(remaining);
            String key = parseKey(remaining);
            remaining = consume(remaining, key);

            remaining = consumeWhiteSpace(remaining);
            if (!remaining.startsWith("=")) {
                throw new IllegalArgumentException("Assignment Operator (" + assignmentOperator + ") between key and value was expected but was missing at: ^" + remaining + ". \nThe input was: " + input);
            }
            remaining = consume(remaining, "=");
            remaining = consumeWhiteSpace(remaining);

            Object value = parseValue(remaining);
            if (value instanceof String) {
                remaining = consume(remaining, (String) value);
            } else if (value instanceof Map) {
                remaining = consume(remaining, fromMap((Map<?, ?>) value));
            }

            result.put(key, value);

            remaining = consumeWhiteSpace(remaining);

            if (remaining.startsWith(mapSuffix)) {
                break;
            } else if (remaining.startsWith(";")) {
                remaining = consume(remaining, ";");
                remaining = consumeWhiteSpace(remaining);
            } else {
                throw new IllegalArgumentException("Delimiter (" + delimiter + ") or map ending (" + mapSuffix + ") was expected but was missing at: ^" + remaining + ". \nThe input was: " + input);
            }
        }

        return result;
    }

    // ===================================================================================
    //                                                                           Validator
    //                                                                            ========
    private static boolean validateInput(String input) {
        return input != null && isMap(input);
    }

    private static boolean isMap(String input) {
        return input.startsWith(mapPrefix) && input.endsWith(mapSuffix);
    }

    // ===================================================================================
    //                                                                    General Consumer
    //                                                                            ========
    private static String consume(String input, String expected) {
        if (input.startsWith(expected)) {
            input = input.substring(expected.length());
        }
        return input;
    }

    private static String consumeWhiteSpace(String input) {
        int index = 0;
        while (index < input.length() && Character.isWhitespace(input.charAt(index))) {
            index++;
        }
        return input.substring(index);
    }

    // ===================================================================================
    //                                                                              Parser
    //                                                                            ========
    private static String parseKey(String input) {
        int index = 0;
        while (index < input.length() && !Character.isWhitespace(input.charAt(index))) {
            index++;
        }

        return input.substring(0, index);
    }

    private static Object parseValue(String input) {
        if (isMap(input)) {
            return toMap(input);
        }

        int index = 0;
        while (index < input.length() && !Character.isWhitespace(input.charAt(index))) {
            index++;
        }
        return input.substring(0, index);
    }

    private static String fromMap(Map<?, ?> map) {
        StringJoiner stringJoiner = new StringJoiner(String.format(" %s ", delimiter), String.format("%s ", mapPrefix), String.format(" %s", mapSuffix));
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map) {
                stringJoiner.add(entry.getKey() + String.format(" %s ", assignmentOperator) + fromMap((Map<?, ?>) entry.getValue()));
            } else {
                stringJoiner.add(entry.getKey() + String.format(" %s ", assignmentOperator) + entry.getValue());
            }
        }
        return stringJoiner.toString();
    }
}
