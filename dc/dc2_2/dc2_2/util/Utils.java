package dc2_2.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    /**
     * 現在の時刻をformatterのフォーマットに従って成型しStringを返す
     *
     * @return String
     */
    public static String getCurrentTime() {
        return formatter.format(Calendar.getInstance().getTime());
    }

    private Utils() {
    }

}
