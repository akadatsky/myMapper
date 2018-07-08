package com.company;

public class Main {

    public static void main(String[] args) {
        String params = "first_name = Oleg, age = 25";
        MyMapper<User> userMapper = new MyMapper<>();
        User user = userMapper.map(User.class, params);
        System.out.println(user);

        String groupParams = "name = Java, count = 11";
        MyMapper<Group> groupMapper = new MyMapper<>();
        Group group = groupMapper.map(Group.class, groupParams);
        System.out.println(group);

    }

}
