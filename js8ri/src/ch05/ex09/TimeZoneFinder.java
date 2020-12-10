package ch05.ex09;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

/**
 * 再度、 ストリーム 操作 を 使用 し て、 オフセット に 1 時間 未満 の 情報 が 含ま れる すべて の タイム ゾーン を 見つけ なさい。
 */
public class TimeZoneFinder {

    final static long OFFSET = 60 * 60;

    static Stream<String> find() {
        LocalDateTime now = LocalDateTime.now();
        return ZoneId.getAvailableZoneIds().stream().filter(
                id -> {
                    ZonedDateTime time = ZonedDateTime.of(now, ZoneId.of(id));
                    return time.getOffset().getTotalSeconds() < OFFSET && time.getOffset().getTotalSeconds() > -OFFSET;
                }
        );
    }

    public static void main(String[] args) {
        TimeZoneFinder.find().map(ZoneId::of).map(ZonedDateTime::now).forEach(System.out::println);
    }
}
