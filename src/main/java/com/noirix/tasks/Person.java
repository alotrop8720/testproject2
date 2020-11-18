package com.noirix.tasks;

import com.noirix.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Person {
    private Long id;

    private String name;

    private String surname;

    private List<Person> friends;

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person(0l, "Sveta", "Misevich", new ArrayList<Person>()));
        personList.add(new Person(1l, "Tania", "Isevich", new ArrayList<Person>()));
        personList.add(new Person(2l, "Ksenia", "Sevich", new ArrayList<Person>()));
        personList.add(new Person(3l, "Vlad", "Vich", new ArrayList<Person>()));
        personList.add(new Person(4l, "Vadim", "Ich", new ArrayList<Person>()));
        personList.add(new Person(5l, "Anton", "Fich", new ArrayList<Person>()));

        personList.get(0).getFriends().add(personList.get(1));
        personList.get(0).getFriends().add(personList.get(2));

        personList.get(1).getFriends().add(personList.get(3));

        personList.get(2).getFriends().add(personList.get(4));

        personList.get(3).getFriends().add(personList.get(5));

        personList.get(5).getFriends().add(new Person(51l, "Valera", "Pch", new ArrayList<Person>()));

        printFriends(personList.get(0), 2);
    }


    public static void printFriends(Person person, int step){
        System.out.println("[++");
        for(Person p : person.getFriends()){
            System.out.println("======="+ step +" "+ p.getName()+"=========");
            if (p.getFriends().size() != 0 && step != 0){
                printFriends(p, --step);
            }
        }
        System.out.println("++]");
    }

}
