package dc2_4.util;

import java.awt.*;
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


    public static Dimension calcFrameSize(FontMetrics fm) {
        int h = (int) (fm.getHeight() * Consts.PROP_HEIGHT * Consts.PANEL_VS_FRAME_HEIGHT);
        int w = (int) (fm.stringWidth("00:00:00") * Consts.PROP_WIDTH * Consts.PANEL_VS_FRAME_WIDTH);
        return new Dimension(w, h);
    }


    private Utils() {
    }

}
