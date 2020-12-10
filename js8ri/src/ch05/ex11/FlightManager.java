package ch05.ex11;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 帰り の 便 は、 フランクフルト を 14 時 5 分 に 出発 し、 ロサンジェルス に 16 時 40 分 に 到着 し ます。 飛行 時間 は、 何時 間 何分 です か。
 * この よう な 計算 を 処理 できる プログラム を 書き なさい。
 */
public class FlightManager {
    private static final ZoneId mDestination = ZoneId.of("America/Los_Angeles");
    private static final ZoneId mDeparture = ZoneId.of("Europe/Berlin");


    public static LocalDateTime calcArrivalTime(LocalDateTime time, int min) {
        return ZonedDateTime.of(time, mDeparture).plusMinutes(min).withZoneSameInstant(mDestination).toLocalDateTime();
    }

    static Duration between(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(ZonedDateTime.of(startTime, mDeparture), ZonedDateTime.of(startTime, mDestination));
    }

    public static void main(String[] args) {
        LocalDateTime localTime =
                FlightManager.calcArrivalTime(LocalDateTime.of(2020, 12, 25, 15, 5), 60 * 10 + 50);
        System.out.println(localTime);


        LocalDateTime start = LocalDateTime.of(2020, 12, 25, 14, 5);
        LocalDateTime end = LocalDateTime.of(2020, 12, 25, 16, 40);
        Duration duration = between(start, end);
        System.out.println(duration);
    }
}
