package ui;

import model.Dog;

public class Main {
    public static void main(String[] args) {


        Dog dog1 = new Dog(01,"Brownie", "Akita", 32.5,64);
        Dog dog2 = new Dog(02,"Ciko", "Beagle", 10.7,36.4);
        System.out.println(dog1 == dog2);


    }
}
