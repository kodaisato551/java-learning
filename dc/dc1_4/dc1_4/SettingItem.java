package dc1_4;

import java.awt.Color;

public class SettingItem {

	private String fontSize;

	private String fontType;

	private String fontColor;

	private String backGroundColor;

	private static SettingItem instance = new SettingItem();

	private SettingItem() {
		changeDefaultSetting();
	}

	public static SettingItem getInstance() {
		return instance;
	}

	public int getFontSize() {
		return FontSize.keyToIntSize(this.fontSize);
	}

	public String getFontSizeString() {
		return fontSize;
	}

	public String getFontType() {
		return fontType;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public Color getFontColor() {
		return SupportedColor.getColor(fontColor);
	}

	public String getFontColorString() {
		return fontColor;
	}

	public Color getBackGroundColor() {
		return SupportedColor.getColor(backGroundColor);
	}

	public String getBackGroundColorString() {
		return backGroundColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public void setFontType(String fontType) {
		this.fontType = fontType;
	}

	public void setBackGroundColor(String backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

	private void changeDefaultSetting() {
		fontSize = FontSize.MEDIUM.getName();
		fontType = ConstSettingParam.getFontTypeList().get(0);//arial
		fontColor = SupportedColor.BLACK.getName();
		backGroundColor = SupportedColor.WHITE.getName();
	}

	public void getFiled() {
		System.out.println("font size : " + fontSize);
		System.out.println("font type : " + fontType);
		System.out.println("font color : " + fontColor);
		System.out.println("back ground ; " + backGroundColor);
	}
}
