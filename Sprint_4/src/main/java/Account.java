import java.util.Objects;

public class Account {
    private final String name;

    public Account(String name) {
        this.name = name;
    }

    public boolean checkNameToEmboss() {
        /*
             Этот метод должен проверять, что сохранённая через конструктор строка соответствует требованиям.
             Если строка удовлетворяет условиям, метод возвращает true, иначе — false.
         */
        if (Objects.isNull(name)) return false;

        if (name.isEmpty()) return false;

        return (name.length() >= 3) &&
                (name.length() <= 19) &&
                (name.matches("^\\S+\\s\\S+$"));
    }
}
