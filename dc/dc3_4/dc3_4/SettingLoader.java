package dc3_4;


import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    public static void savePrefs(Stage stage) {
        Color fontColor = currentSetting.getFontColor();
        Color bgColor = currentSetting.getBgColor();
        pref.put(FONT_TYPE_NAME, currentSetting.getFontStyle());
        pref.putInt(FONT_SIZE, currentSetting.getFontSize());
        pref.put(FONT_COLOR_NAME, currentSetting.getFontColor().toString());
        pref.put(BACKGROUND_COLOR_NAME, currentSetting.getBgColor().toString());
        pref.putDouble(START_POINT_X, stage.getX());
        pref.putDouble(START_POINT_Y, stage.getY());
        dc2_4.setting.Setting.getInstance().printStatus("Saved");

    }

    //perfのload
    public static void loadPrefs() {
        dc2_4.setting.Setting.getInstance().printStatus("Load Before");
        String fontTypeName = pref.get(FONT_TYPE_NAME, dc2_4.setting.SupportedSettings.FONT_TYPE_LIST.get(0));
        int fontSize = pref.getInt(FONT_SIZE, dc2_4.setting.DefaultProperties.FONT.getSize());
        String fontColorString = pref.get(FONT_COLOR_NAME, dc2_4.setting.SupportedSettings.COLOR_VS_STRING_MAP.get(dc2_4.setting.DefaultProperties.FONT_COLOR));
        String bgColorString = pref.get(BACKGROUND_COLOR_NAME, dc2_4.setting.SupportedSettings.COLOR_VS_STRING_MAP.get(DefaultProperties.BG_COLOR));
        double pointX = pref.getDouble(START_POINT_X, 0);
        double pointY = pref.getDouble(START_POINT_Y, 0);

        System.out.println("pref fontColor " + fontColorString);
        System.out.println("pref bgColor " + bgColorString);

        currentSetting.setFontStyle(fontTypeName);
        currentSetting.setFontSize(fontSize);
        currentSetting.setFontColor(Color.valueOf(fontColorString));
        currentSetting.setBgColor(Color.valueOf(bgColorString));
        currentSetting.setCurrentPoint(new Dimension2D(pointX, pointY));

        Setting.getInstance().printStatus("Load After");
    }


}
