package dc1_4;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.List;

/**
 * TODO w:デフォルト設定の追加
 * @author Sato Kodai
 *
 */
public class ConstSettingParam {
	private ConstSettingParam() {
	};

	/* font size とframeの比率*/
	private static final int PROP_HEIGHT = 6;

	private static final int PROP_WIDTH = 2;

	private static final List<String> FONT_TYPE_LIST = Arrays
			.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

	public static int getPropHeight() {
		return PROP_HEIGHT;
	}

	public static int getPropWidth() {
		return PROP_WIDTH;
	}

	public static List<String> getFontTypeList() {
		return FONT_TYPE_LIST;
	}

	public static final String FONT_TYPE_NAME = "Font Type";
	public static final String FONT_COLOR_NAME = "Font Color";
	public static final String FONT_SIZE_NAME = "Font Size";
	public static final String BACKGROUND_COLOR_NAME = "Back Ground Color";

	public static final String START_POINT_X = "Start Point X";
	public static final String START_POINT_Y = "Start Point Y";
}
