package ch04.ex02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * チャート や テーブル といった 多く の JavaFX プロパティ を 持つ クラス を 考え なさい。
 * 特定 の アプリケーション では、 ほとんど の プロパティ には 決して リスナー が 登録 さ れ ない 可能性 が 高い です。
 * したがって、 プロパティ ごと に プロパティオブジェクト を 持つ こと は 無駄 です。
 * プロパティ 値 を 保存 する ため に 最初 に 普通 の フィールド を 使用 し て、 初めて xxxProperty() メソッド が 呼び出さ れ た とき にだけ プロパティオブジェクト を 使用 する よう に、
 * 要求 に 応じ て プロパティ を 構築 する 方法 を 示し なさい。
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
