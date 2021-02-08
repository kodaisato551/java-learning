import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * メニューのUI設定
 * メニューをスタティックファクトリで返す
 */
public class PropMenu {
    private static final Menu fontColor = new Menu("Font Color");
    private static final Menu fontSize = new Menu("Font Size");
    private static final Menu fontType = new Menu("Font Type");
    private static final Menu bgColor = new Menu("Background Color");
    private static final Menu terminate = new Menu("Terminate");
    private static final Setting setting = Setting.getInstance();


    public static ContextMenu create() {
        ContextMenu menu = new ContextMenu();
        setupFontType();
        menu.getItems().addAll(fontType, fontSize, fontColor, bgColor, terminate);
        return menu;
    }

    private static void setupFontType() {
        MenuItem[] items = new MenuItem[SupportedSettings.FONT_TYPE_LIST.size()];
        for (int i = 0; i < items.length; i++) {
            String name = SupportedSettings.FONT_TYPE_LIST.get(i);
            items[i] = new MenuItem(name);
            items[i].setOnAction(e -> setting.setFontStyle(name));
        }
        fontType.getItems().addAll(items);
    }

    private PropMenu() {
    }
}
