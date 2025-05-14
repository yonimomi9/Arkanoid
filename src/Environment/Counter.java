/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Counter.java
 */
package Environment;

/**
 * The type Counter.
 */
public class Counter {
    private int num;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.setNum(0);
    }

    /**
     * Instantiates a new Counter.
     *
     * @param n the n
     */
    public Counter(int n) {
        this.setNum(n);
    }

    /**
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.setNum(this.getValue() + number);
    }

    /**
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.setNum(this.getValue() - number);
    }

    /**
     * get current count's value.
     *
     * @return the value
     */
    public int getValue() {
        return this.num;
    }

    /**
     * Val to string string.
     *
     * @return the string
     */
    public String valtoString() {
        return Integer.toString(this.num);
    }

    /**
     * sets the Counter to the param.
     *
     * @param n the number
     */
    private void setNum(int n) {
        this.num = n;
    }
}
