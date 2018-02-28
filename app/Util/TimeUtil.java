package Util;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.Random;

/**
 * Created by dliu15 on 2/19/18.
 */
public class TimeUtil {

    public static boolean isExpired(Timestamp expiration) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        return expiration.before(now);
    }

}