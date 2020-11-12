package ch04.ex03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ほとんど が デフォルト から 変化 し ない 多く の JavaFX プロパティ を 持つ クラス を 考え なさい。
 * デフォルト では ない 値 に 設定 さ れ たり、 xxxProperty() メソッド が 最初 に 呼び出さ れ た とき に、 要求 に 応じ て プロパティ を 構築 する 方法 を
 */
public class PropertyConstractor<T> {
    private StringProperty textProperty = null;
    private String text = "";

    public final StringProperty textProperty() {
        if (textProperty == null) {
            textProperty = new SimpleStringProperty(text);
        }
        return textProperty;
    }

    public final void setText(String newValue) {
        text = newValue;
        if (textProperty != null) {
            textProperty.set(text);
        }
    }

    public final String getText() {
        return text;
    }
}
