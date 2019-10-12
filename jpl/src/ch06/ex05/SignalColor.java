package ch06.ex05;

/**
 *
 * 各定数値に依存して実装が異なるわけではなく、
 * 実装はすべて同じだが返されるオブジェクトは同一でその内部のフィールドが異なるでけなので、
 * ch06.ex04の状態でよいと思うので、推奨しない
 * @author Sato Kodai
 *
 */
public enum SignalColor {
	BLUE {
		@Override
		public Color getColor() {
			return new Color("blue");
		}
	},
	YELLOW {
		@Override
		public Color getColor() {
			return new Color("yellow");
		}
	},
	RED {
		@Override
		public Color getColor() {
			return new Color("red");
		}
	};

	public abstract Color getColor();

}
