public class Util {
    public double getRandomNum(Integer maxNum) {
        return (double) Math.floor(Math.random() * maxNum);
    }

    public int getRandomInt(Integer maxNum) {
        return (int) getRandomNum(maxNum);
    }
}
