package aoc2021.day14;

import java.util.Map;

public class Input {
    private final String template;
    private final Map<String, Character> rules;

    public Input(String template, Map<String, Character> rules) {
        this.template = template;
        this.rules = Map.copyOf(rules);
    }

    public String getTemplate() {
        return template;
    }

    public Map<String, Character> getRules() {
        return rules;
    }

}
