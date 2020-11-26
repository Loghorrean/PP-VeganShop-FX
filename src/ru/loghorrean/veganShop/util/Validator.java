package ru.loghorrean.veganShop.util;

public class Validator {
    public static boolean validateAllFields(String ...fields) {
        for (String field: fields) {
            if (field.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean validatePhone(String phone) {
        return RegexCompiler.compileRegEx(phone, Regexes.PHONE_NUMBER);
    }

    public static boolean validateMail(String mail) {
        return RegexCompiler.compileRegEx(mail, Regexes.MAIL);
    }

    public static boolean validatePass(String password) {
        return RegexCompiler.compileRegEx(password, Regexes.PASSWORD);
    }
}
