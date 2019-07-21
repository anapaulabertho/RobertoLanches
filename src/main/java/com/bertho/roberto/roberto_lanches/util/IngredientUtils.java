package com.bertho.roberto.roberto_lanches.util;

import com.bertho.roberto.roberto_lanches.entity.Ingredient;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class IngredientUtils {

    public static Map<Ingredient, Integer> getQuantityOfEachIngredient(List<Ingredient> ingredients) {
        return ingredients.stream().collect(groupingBy(e -> e, counting()))
                .entrySet().stream().collect(toMap(Map.Entry::getKey, e -> e.getValue().intValue()));
    }

    public static Double getAllIngredientsPrice(Map<Ingredient, Integer> qttIngredients) {
        return qttIngredients.entrySet().stream().map(e -> e.getKey().getPrice()*e.getValue())
                .reduce(0.0, (subtotal, element) -> subtotal + element);
    }
}
