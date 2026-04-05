public class PasswordEven {
    public static void main(String[] args) {
        users.sort((u1, u2) -> {
            boolean u1Even = u1.getPasswordId() % 2 == 0;
            boolean u2Even = u2.getPasswordId() % 2 == 0;

            if (u1Even && !u2Even) return -1; // Чётный раньше нечётного
            if (!u1Even && u2Even) return 1;  // Нечётный позже чётного
            return 0; // Внутри групп порядок не меняется
        });
    }
}
