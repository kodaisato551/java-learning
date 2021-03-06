package dc2_1;

import java.text.SimpleDateFormat;
import java.util.Calendar;

class Utils {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    /**
     * 現在の時刻をformatterのフォーマットに従って成型しStringを返す
     *
     * @return String
     */
    static String getCurrentTime() {
        return formatter.format(Calendar.getInstance().getTime());
    }

    private Utils() {
    }

}
