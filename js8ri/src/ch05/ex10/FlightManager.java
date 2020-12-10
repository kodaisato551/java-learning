package ch05.ex10;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * ロサンジェルス から フランクフルト 行き の 便 は、 ローカル 時刻 の 午後 3 時 5 分 に 出発 し、 10 時間 50 分の 飛行 です。 何時 に 到着 し ます か。
 * この よう な 計算 を 処理 できる プログラム を 書き なさい。
 */
public class FlightManager {
    private static final ZoneId mDeparture = ZoneId.of("America/Los_Angeles");
    private static final ZoneId mDestination = ZoneId.of("Europe/Berlin");

    public static LocalDateTime calcArrivalTime(LocalDateTime time, int min) {
        return ZonedDateTime.of(time, mDeparture).plusMinutes(min).withZoneSameInstant(mDestination).toLocalDateTime();
    }

    public static void main(String[] args) {
        LocalDateTime localTime =
                FlightManager.calcArrivalTime(LocalDateTime.of(2020, 12, 25, 15, 5), 60 * 10 + 50);
        System.out.println(localTime);
    }
}
