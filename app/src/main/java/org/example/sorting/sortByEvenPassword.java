import java.util.Comparator;

public class sortByEvenPassword implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        boolean u1Even = u1.getPasswordId() % 2 == 0;
        boolean u2Even = u2.getPasswordId() % 2 == 0;

        if (u1Even && !u2Even) return -1; // Чётный раньше нечётного
        if (!u1Even && u2Even) return 1;  // Нечётный позже чётного
        return 0; // Внутри групп порядок не меняется
    }
}
