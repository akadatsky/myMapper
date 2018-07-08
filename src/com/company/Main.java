package com.company;

public class Main {

    public static void main(String[] args) {
        String params = "firstName = Oleg, age = 25";
        MyMapper<User> userMapper = new MyMapper<>();
        User user = userMapper.map(User.class, params);
        System.out.println(user);
    }

}
