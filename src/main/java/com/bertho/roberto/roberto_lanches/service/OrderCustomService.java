package com.bertho.roberto.roberto_lanches.service;

import com.bertho.roberto.roberto_lanches.entity.Ingredient;
import com.bertho.roberto.roberto_lanches.repository.IngredientsRepository;
import com.bertho.roberto.roberto_lanches.util.IngredientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bertho.roberto.roberto_lanches.util.IngredientUtils.getAllIngredientsPrice;
import static java.util.stream.Collectors.*;

@Service
public class OrderCustomService {

    @Autowired
    IngredientsRepository ingredientsRepository;

    public Double OrderCustom(List<Integer> ingredientsIds) {
        List<Ingredient> ingredients = new ArrayList();
        ingredientsIds.forEach(e -> ingredients.add(ingredientsRepository.getIngredientsById(e)));

        Map<Ingredient, Integer> qttIngredients = IngredientUtils.getQuantityOfEachIngredient(ingredients);

        Double totalPrice = IngredientUtils.getAllIngredientsPrice(qttIngredients);

        return totalPrice;
    }

}
