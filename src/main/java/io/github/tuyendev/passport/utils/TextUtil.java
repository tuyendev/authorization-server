package io.github.tuyendev.passport.utils;

import io.github.tuyendev.passport.exception.LogicException;
import org.apache.tomcat.util.buf.HexUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.UUID;

public class TextUtil {

    public static String generateUuid() {
        try {
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
            return HexUtils.toHexString(salt.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new LogicException("cannot generate uuid", e);
        }
    }

    public static String unsigned(String src) {
        String dest = Normalizer.normalize(src, Normalizer.Form.NFD);
        return dest.replaceAll("[^\\p{ASCII}]", "");
    }
}
