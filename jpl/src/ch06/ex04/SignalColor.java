package ch06.ex04;

/**
 * @author Sato Kodai
 *
 */
public enum SignalColor {
	BLUE(new Color("blue")), YELLOW(new Color("yellow")), RED(new Color("red"));

	private Color color;

	private SignalColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}
