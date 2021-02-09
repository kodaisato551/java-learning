import javafx.geometry.Dimension2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Set;

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
    private final ContextMenu mMenu;
    private final Stage mStage;

    public PropMenu(Stage stage) {
        mStage = stage;
        mMenu = new ContextMenu();
        setupFontType();
        setupFontSize();
        setupFontColor();
        setupBgColor();
        terminate.setOnAction(e -> System.exit(1));
        mMenu.getItems().addAll(fontType, fontSize, fontColor, bgColor, terminate);
    }

    public ContextMenu getMenu() {
        return mMenu;
    }

    private void setupFontType() {
        MenuItem[] items = new MenuItem[SupportedSettings.FONT_TYPE_LIST.size()];
        for (int i = 0; i < items.length; i++) {
            String name = SupportedSettings.FONT_TYPE_LIST.get(i);
            items[i] = new MenuItem(name);
            items[i].setOnAction(e -> setting.setFontStyle(name));
        }
        fontType.getItems().addAll(items);
    }

    private void setupFontSize() {
        Set<String> fontSizeNames = SupportedSettings.FONT_SIZE_MAP.keySet();
        MenuItem[] items = new MenuItem[fontSizeNames.size()];
        int count = 0;
        for (String name : fontSizeNames) {
            items[count] = new MenuItem(name);
            items[count].setOnAction(e -> {
                setting.setFontSize(SupportedSettings.FONT_SIZE_MAP.get(name));
                Dimension2D window = setting.calcPreferredWindowSize();
                mStage.setHeight(window.getHeight());
                mStage.setWidth(window.getWidth());
            });
            count++;
        }
        fontSize.getItems().addAll(items);

    }

    private void setupFontColor() {
        Set<String> colors = SupportedSettings.SUPPORTED_COLOR.keySet();
        MenuItem[] items = new MenuItem[colors.size()];
        int count = 0;
        for (String name : colors) {
            Color color = SupportedSettings.SUPPORTED_COLOR.get(name);
            Rectangle rectangle = new Rectangle(10, 10);
            rectangle.setFill(color);
            items[count] = new MenuItem(name, rectangle);
            items[count].setOnAction(e -> {
                setting.setFontColor(color);
            });
            count++;
        }
        fontColor.getItems().addAll(items);
    }

    private void setupBgColor() {
        Set<String> colors = SupportedSettings.SUPPORTED_COLOR.keySet();
        MenuItem[] items = new MenuItem[colors.size()];
        int count = 0;
        for (String name : colors) {
            Color color = SupportedSettings.SUPPORTED_COLOR.get(name);
            Rectangle rectangle = new Rectangle(10, 10);
            rectangle.setFill(color);
            items[count] = new MenuItem(name, rectangle);
            items[count].setOnAction(e -> {
                setting.setBgColor(color);
            });
            count++;
        }
        bgColor.getItems().addAll(items);
    }

}
