package codeswarm.io.perun.helper;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordHelper {

    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public boolean isPasswordValid(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    /**
     * Method for password validation according to the next constraints:
     * .{8,} - the password has to be longer than 7 chars (at least 8)
     * (?=.*[0-9]) - the password has to include at least one digit
     * (?=\S+$) - the whitespaces are not allowed
     * @param password
     * @return
     */
    public static boolean isPasswordStrong(final String password) {
        String regex = "(?=.*[0-9])(?=\\S+$).{8,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
