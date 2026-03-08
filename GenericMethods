import java.util.*;
import java.util.function.*;

public class GenericMethods {

    // 3.1 map: применить функцию к каждому элементу
    public static <T, P> List<P> map(List<T> list, Function<T, P> func) {

        List<P> result = new ArrayList<>();

        for (T item : list) {
            result.add(func.apply(item));
        }

        return result;
    }

    // 3.2 filter: оставить элементы по условию
    public static <T> List<T> filter(List<T> list, Predicate<T> pred) {

        List<T> result = new ArrayList<>();

        for (T item : list) {
            if (pred.test(item)) {
                result.add(item);
            }
        }

        return result;
    }

    // 3.3 reduce: "свернуть" список в одно значение (без ошибок на пустом)
    public static <T> T reduce(List<T> list, BinaryOperator<T> op, T defaultValue) {

        if (list == null || list.isEmpty()) {
            return defaultValue;
        }

        T res = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            res = op.apply(res, list.get(i));
        }

        return res;
    }

    // 3.4 collect: собрать элементы в любую структуру (Map, Set, List...)
    public static <T, P> P collect(List<T> list, Supplier<P> factory, BiConsumer<P, T> accumulator) {

        P result = factory.get();

        for (T item : list) {
            accumulator.accept(result, item);
        }

        return result;
    }
}
