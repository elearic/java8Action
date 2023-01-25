package ericzz.java8.base.pojo;

public class HashCodeMain {

    public static void main1(String[] args) {

        Apple apple1 = new Apple();
//        apple1.setColor("red");

        Apple apple2 = new Apple();
//        apple2.setColor("red");

        System.out.println("apple1 == apple2:    " + apple1.equals(apple2));
        System.out.println("apple1 hashcode:    " + apple1.hashCode());
        System.out.println("apple2 hashcode:    " + apple2.hashCode());
    }

    public static void main(String[] args) {
        Banana banana = new Banana();
        banana.setPrice(100);


        Integer price = banana.getPrice();
        price = price + 1;

        System.out.println(banana.getPrice());


//        FruitGroup fruitGroup = new FruitGroup();
//        List<Banana> list = new LinkedList<Banana>();
//
//        fruitGroup.setBananaList(list);
//
//        list.add(new Banana());
//        list.add(new Banana());
//        list.add(new Banana());
//
//
//        List<Banana> list1 = fruitGroup.getBananaList();
//
//        list1.clear();
//
//        System.out.println(fruitGroup.getBananaList());


    }
}