package dc2_4.setting;

import java.awt.*;
import java.util.prefs.Preferences;

public class SettingLoader {

    private static final Setting currentSetting = Setting.getInstance();
    private static final Preferences pref = Preferences.userRoot().node("digital_clock");


    private static final String FONT_TYPE_NAME = "Font Type";
    private static final String FONT_COLOR_NAME = "Font Color";
    private static final String FONT_SIZE = "Font Size";
    private static final String BACKGROUND_COLOR_NAME = "Back Ground Color";

    private static final String START_POINT_X = "Start Point X";
    private static final String START_POINT_Y = "Start Point Y";

    //prefの保存
    public static void savePrefs() {
        Color fontColor = currentSetting.getFontColor();
        Color bgColor = currentSetting.getBgColor();
        pref.put(FONT_TYPE_NAME, currentSetting.getCurrentFont().getFamily());
        pref.putInt(FONT_SIZE, currentSetting.getCurrentFont().getSize());
        pref.put(FONT_COLOR_NAME, SupportedSettings.COLOR_VS_STRING_MAP.get(fontColor));
        pref.put(BACKGROUND_COLOR_NAME, SupportedSettings.COLOR_VS_STRING_MAP.get(bgColor));
        Point point = currentSetting.getCurrentPoint();
        pref.putInt(START_POINT_X, point.x);
        pref.putInt(START_POINT_Y, point.y);
        Setting.getInstance().printStatus("Saved");

    }

    //perfのload
    public static void loadPrefs() {
        Setting.getInstance().printStatus("Load Before");
        String fontTypeName = pref.get(FONT_TYPE_NAME, SupportedSettings.FONT_TYPE_LIST.get(0));
        int fontSize = pref.getInt(FONT_SIZE, DefaultProperties.FONT.getSize());
        String fontColorString = pref.get(FONT_COLOR_NAME, SupportedSettings.COLOR_VS_STRING_MAP.get(DefaultProperties.FONT_COLOR));
        String bgColorString = pref.get(BACKGROUND_COLOR_NAME, SupportedSettings.COLOR_VS_STRING_MAP.get(DefaultProperties.BG_COLOR));
        int pointX = pref.getInt(START_POINT_X, 0);
        int pointY = pref.getInt(START_POINT_Y, 0);

        System.out.println("pref fontColor " + fontColorString);
        System.out.println("pref bgColor " + bgColorString);

        currentSetting.setFont(fontTypeName, fontSize);
        currentSetting.setFontColor(SupportedSettings.SUPPORTED_COLOR.get(fontColorString));
        currentSetting.setBgColor(SupportedSettings.SUPPORTED_COLOR.get(bgColorString));
        currentSetting.setCurrentPoint(new Point(pointX, pointY));

        Setting.getInstance().printStatus("Load After");
    }


}
