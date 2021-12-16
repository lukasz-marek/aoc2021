package aoc2021.day14;

import java.util.Map;

public class Input {
    private final String template;
    private final Map<String, String> rules;

    public Input(String template, Map<String, String> rules) {
        this.template = template;
        this.rules = Map.copyOf(rules);
    }

    public String getTemplate() {
        return template;
    }

    public Map<String, String> getRules() {
        return rules;
    }

}
