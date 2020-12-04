package ch05.ex08;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 現在 の 時刻 インスタント に対して サポート さ れる すべて の タイム ゾーン において、
 * 今日 の 日付 の オフセット（ UTC との 差） を 取得 し なさい。 その 際、 ZoneId. getAvailableZoneIds() を ストリーム へ 変換 し
 * てから、 ストリーム 操作 を 使用 する こと によって 取得 し なさい。
 */
public class TimerZone {

    public void showOffeset(LocalDateTime now) {
        ZoneId.getAvailableZoneIds().stream().forEach(id -> {
                    ZonedDateTime time =
                            ZonedDateTime.of(now, ZoneId.of(id));
                    System.out.println(time);
                }
        );
    }

    public static void main(String[] args) {
        TimerZone zone = new TimerZone();
        zone.showOffeset(LocalDateTime.now());
    }
}