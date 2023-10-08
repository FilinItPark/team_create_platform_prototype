package team.platform.entity;

/**
 * @author 1ommy
 * @version 24.09.2023
 */
public class Pair<T, V> {
    private T firstValue;
    private V secondValue;

    public Pair(T firstValue, V secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public T getFirstValue() {
        return firstValue;
    }

    public V getSecondValue() {
        return secondValue;
    }
}
