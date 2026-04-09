import java.util.Comparator;

public class SortByEvenPassword implements Comparator<User> {
    @Override
    public void sort(List<User> users) {
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
