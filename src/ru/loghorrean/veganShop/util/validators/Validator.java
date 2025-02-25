package ru.loghorrean.veganShop.util.validators;

import ru.loghorrean.veganShop.util.RegexCompiler;
import ru.loghorrean.veganShop.util.Regexes;

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

    public static boolean checkForInt(String field) {
        try {
            int i = Integer.parseInt(field);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static boolean checkForFloat(String field) {
        try {
            float i = Float.parseFloat(field);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
