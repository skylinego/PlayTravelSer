package Util;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

/**
 * Created by dliu15 on 2/19/18.
 */
public class EncryptUtil {

    private static Random rand = new Random((new Date()).getTime());

    public static String encrypt(String str) {

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] salt = new byte[8];
        rand.nextBytes(salt);
        return encoder.encodeToString(salt) + encoder.encodeToString(str.getBytes());
    }

    public static String decrypt(String encstr) {

        if (encstr.length() > 12) {

            String cipher = encstr.substring(12);
            Base64.Decoder decoder = Base64.getDecoder();
            try {
                return new String(decoder.decode(cipher));
            } catch (Exception e) {
                //  throw new InvalidImplementationException(
                //Fail
            }

        }
        return null;
    }
}