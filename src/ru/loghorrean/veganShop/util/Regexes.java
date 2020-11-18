package ru.loghorrean.veganShop.util;

public enum Regexes {
    MAIL("^.+@.+\\..+$"),
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$g"),
    PHONE_NUMBER("^(+)?(d[-]?){9,13}(d)$");
    private String regex;
    Regexes(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return this.regex;
    }
}
