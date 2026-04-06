package org.example.io.input;

import org.example.model.User;
import org.example.util.CustomArrayList;
import org.example.util.CustomCollection;
import com.ibm.icu.text.Transliterator;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomUserGenerator implements Input {
    private final int countElements;
    private static final String[] names = "Александр,Алексей,Андрей,Антон,Артем,Борис,Вадим,Валентин,Валерий,Василий,Виктор,Владимир,Владислав,Денис,Яна,Mary,Robert,Patricia,Jennifer,Linda,Elizabeth,David,Joseph,Susan,Thomas,Jessica,Christopher,Karen".split(",");
    private static final String[] domains = "gmail.com,yandex.ru,mail.ru,inbox.ru,list.ru,bk.ru,rambler.ru,outlook.com,hotmail.com,yahoo.com,aol.com,protonmail.com,mail.com,gmx.com,icloud.com".split(",");
    private final Random random = new Random();
    private static final Transliterator TRANSLITERATOR = Transliterator.getInstance("Russian-Latin/BGN");

    public RandomUserGenerator(int countElements) {
        this.countElements = countElements;
    }

    @Override
    public CustomCollection<User> generateUsers() {
        return IntStream.range(0, countElements)
                .mapToObj(i -> createRandomUsers(i))
                .collect(
                        CustomArrayList::new,
                        CustomCollection::add,
                        (col1, col2) -> {});
    }

    private String getRandomName() {
        String randomName = names[this.random.nextInt(names.length)];
        return randomName;
    }

    private String generateRandomPassword() {
        StringBuilder result = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index = this.random.nextInt(10);
            result.append(index);
        }

        return result.toString();
    }

    private String generateRandomEmail(String name, int index) {
        StringBuilder sb = new StringBuilder();
        String latinName = transliterate(name);
        String randomDomain = domains[this.random.nextInt(domains.length)];
        String email = sb.append(latinName.toLowerCase()).append(index).append("@").append(randomDomain.toLowerCase()).toString();
        return email;
    }

    private User createRandomUsers(int index){
        String randomName = getRandomName();

        User user = new User.Builder()
                .setName(randomName)
                .setPassword(generateRandomPassword())
                .setEmail(generateRandomEmail(randomName, index))
                .create();
        return user;
    }

    private String transliterate(String name) {
        return TRANSLITERATOR.transliterate(name);
    }
}
