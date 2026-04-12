package org.example.service;

import org.example.io.output.Output;
import org.example.model.User;
import org.example.sorting.QuickSort;
import org.example.sorting.Sorting;
import org.example.sorting.UserComparators;
import org.example.sorting.UserPasswordSorter;
import org.example.util.CustomArrayList;
import org.example.util.ParallelEmailCounter;
import org.example.util.CustomCollection;

public class UserService {
    private final CustomCollection<User> users;
    private final Sorting sorting;

    public UserService(CustomCollection<User> users, Sorting sorting) {
        this.users = users;
        this.sorting = sorting;
    }

    public static UserService createDefault() {
        return new UserService(
                new CustomArrayList<>(),
                new QuickSort()
        );
    }

    public void addUsers(CustomCollection<User> data) {
        users.add(data);
    }

    public void outputUsers(Output output) {
        output.write(users);
    }

    public void sortByName() {
        sorting.sort(users, UserComparators.byName());
    }

    public void sortByEmail() {
        sorting.sort(users, UserComparators.byEmail());
    }

    public void sortByPassword() {
        sorting.sort(users, UserComparators.byPassword());
    }

    public void sortByEvenPassword() {
        UserPasswordSorter.sortByEvenPassword(users);
    }

    public int countByEmailDomain(String input) {
        return ParallelEmailCounter.count(users, input);
    }
}
