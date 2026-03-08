public class Box<T> {

    private T value;

    public boolean isEmpty() {
        return value == null;
    }

    public void put(T value) {
        if (!isEmpty()) {
            throw new IllegalStateException("Коробка не пуста!");
        }
        this.value = value;
    }

    public T take() {
        if (isEmpty()) return null;

        T temp = value;
        value = null; // обязательно обнуляем ссылку
        return temp;
    }

    @Override
    public String toString() {
        return isEmpty() ? "Box{empty}" : "Box{value=" + value + "}";
    }
}
