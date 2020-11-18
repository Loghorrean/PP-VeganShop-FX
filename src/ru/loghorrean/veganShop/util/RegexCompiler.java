package ru.loghorrean.veganShop.util;

public class RegexCompiler {
    public static boolean compileRegEx(String expression, Regexes regex) {
        return expression.matches(regex.getRegex());
    }
}
