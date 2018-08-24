package com.tangYong.Ioc;

public class Birder {
    @Cell(value = "com.tangYong.Ioc.Animal")
    private static Animal animal;
    @Cell(value = "180")
    private String weight;
    @Cell(value = "birder")
    private String type;

    public static Animal getAnimal() {
        return animal;
    }

    public static void setAnimal(Animal animal) {
        Birder.animal = animal;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
