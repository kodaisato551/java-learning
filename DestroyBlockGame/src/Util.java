public class Util {

    /**
     * mini以上max未満の乱数を返す。
     * @param min
     * @param max
     * @return
     */
    public static int getRandInt(int min, int max){
        return (int) (Math.random() * (max-min)) + min;
    }
}
