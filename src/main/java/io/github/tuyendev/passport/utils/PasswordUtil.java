package io.github.tuyendev.passport.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PasswordUtil {

    private static final Random RANDOM = new Random();

    public static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(RANDOM.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(RANDOM.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(RANDOM.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(RANDOM.nextInt(numbers.length()));

        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(RANDOM.nextInt(combinedChars.length()));
        }
        return password;
    }

    public static boolean isPasswordMatched(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
