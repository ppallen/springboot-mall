package com.allen.springbootmall.constant;

public class MyTest {

    public static void main(String[] args) {
        ProductCategory category = ProductCategory.FOOD;
        String s = category.name();
        System.out.println("s:" + s);

        String s2 = "CAR";
        ProductCategory category2 = ProductCategory.valueOf(s2);//確認Category2是否存在s2的字串
        System.out.println(category2);

    }


}
