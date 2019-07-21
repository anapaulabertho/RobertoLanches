package com.bertho.roberto.roberto_lanches.util;

import java.util.List;

public class PromotionUtils {

    public static Double light(List<Integer> ingredientsIds, Double price, Integer idBacon, Integer idLettuce) {
        if(ingredientsIds.stream().anyMatch(e -> e.equals(idLettuce)) && ingredientsIds.stream().noneMatch(e -> e.equals(idBacon))) {
            price = price*0.9;
        }
        return price;
    }

    public static Double tooMuch(List<Integer> ingredientsIds, Double price, Integer id, Double ingredientPrice) {
        if(ingredientsIds.stream().anyMatch(e -> e.equals(id))) {
            Long qtt = ingredientsIds.stream().filter(i -> i.equals(id)).count();

            if(qtt%3 == 0) {
                price = price - (qtt*ingredientPrice);
                price = price + ((qtt-(qtt/3))*ingredientPrice);
            }
        }
        return price;
    }
}
