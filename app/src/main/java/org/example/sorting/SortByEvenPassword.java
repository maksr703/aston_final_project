import java.util.*;

public class UserPasswordSorter {
    #public static void sortByEvenPassword(List<User> users) {
        List<Map.Entry<Integer, User>> evenPasswordUsers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).isPasswordEven()) {
                evenPasswordUsers.add(new AbstractMap.SimpleEntry<>(i, users.get(i)));
            }
        }
        evenPasswordUsers.sort(Comparator.comparing(e -> {
            try {
                return Integer.parseInt(e.getValue().getPassword());
            } catch (NumberFormatException ex) {
                System.err.println("Ошибка преобразования пароля: " + e.getValue().getPassword());
                return 0;
            }
        }));
        Iterator<Map.Entry<Integer, User>> evenIterator = evenPasswordUsers.iterator();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).isPasswordEven() && evenIterator.hasNext()) {
                users.set(i, evenIterator.next().getValue());
            }
        }
    }
}
