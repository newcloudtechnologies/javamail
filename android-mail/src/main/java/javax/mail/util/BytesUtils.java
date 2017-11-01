package javax.mail.util;

import android.os.Build;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public class BytesUtils {

    public static byte[] getBytesUTF8(String s) {
        byte[] bytes;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bytes= s.getBytes(StandardCharsets.UTF_8);

        } else {
            bytes = getBytes(s, "UTF-8");
        }
        return bytes;
    }

    private static byte[] getBytes(String s, String charsetName) {
        byte[] bytes;
        try {
            bytes = s.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            bytes = s.getBytes();
        }
        return bytes;
    }

    public static byte[] getBytesUS_ASCII(String s) {
        byte[] bytes;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bytes= s.getBytes(StandardCharsets.US_ASCII);

        } else {
            bytes = getBytes(s, "US-ASCII");
        }
        return bytes;
    }

    public static byte[] getBytesISO_8859_1(String s) {
        byte[] bytes;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bytes= s.getBytes(StandardCharsets.ISO_8859_1);

        } else {
            bytes = getBytes(s, "ISO-8859-1");
        }
        return bytes;
    }
}
