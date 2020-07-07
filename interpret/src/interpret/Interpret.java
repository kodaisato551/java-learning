package interpret;

import interpret.ui.CreateInstanceUIFrame;

/**
 * wait()の呼び出して正しく呼び出せない
 *　配列が生成できない
 *　自分自身が呼べない > 呼べるように修正
 *
 * 障害：
 * 最初の画面起動（CreateInstanceUIFrame）＞インスタンス生成＞show object>InvokeUIFrame生成＞InvokeUIFrameをバツで消す
 * CreateInstanceUIFrameで別インスタンスを作る＞show object ＞一回目で生成したインスタンスのメソッドが残る
 * @author Sato Kodai
 */
public class Interpret {
    public static void main(String[] args) {
        System.out.println(args);
        CreateInstanceUIFrame createInstanceUIFrame = new CreateInstanceUIFrame();
        createInstanceUIFrame.setVisible(true);
    }
}
