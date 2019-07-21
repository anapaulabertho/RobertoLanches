package com.bertho.roberto.roberto_lanches.service;

import com.bertho.roberto.roberto_lanches.entity.Ingredient;
import com.bertho.roberto.roberto_lanches.repository.IngredientsRepository;
import com.bertho.roberto.roberto_lanches.util.IngredientUtils;
import com.bertho.roberto.roberto_lanches.util.PromotionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class OrderCustomService {

    @Autowired
    IngredientsRepository ingredientsRepository;

    public Double OrderCustom(List<Integer> ingredientsIds) {
        if(ingredientsRepository.equals(null)) { throw new Error("Fail to instantiate repository"); }
        List<Ingredient> ingredients = new ArrayList();
        ingredientsIds.forEach(e -> ingredients.add(ingredientsRepository.getIngredientsById(e)));

        Map<Ingredient, Integer> qttIngredients = IngredientUtils.getQuantityOfEachIngredient(ingredients);

        Double totalPrice = IngredientUtils.getAllIngredientsPrice(qttIngredients);

        totalPrice = promotionTests(totalPrice, ingredientsIds);

        return totalPrice;
    }

    private Double promotionTests (Double totalPrice, List<Integer> ingredientsIds) {
        Integer idLettuce = ingredientsRepository.getIngredientIdByName("ALFACE");
        Integer idBacon = ingredientsRepository.getIngredientIdByName("BACON");

        totalPrice = PromotionUtils.light(ingredientsIds, totalPrice, idBacon, idLettuce);

        Integer idMeat = ingredientsRepository.getIngredientIdByName("HAMBURGUER DE CARNE");
        Double meatPrice = ingredientsRepository.getIngredientPrice(idMeat);

        totalPrice = PromotionUtils.tooMuch(ingredientsIds, totalPrice, idMeat, meatPrice);

        Integer idCheese = ingredientsRepository.getIngredientIdByName("QUEIJO");
        Double cheesePrice = ingredientsRepository.getIngredientPrice(idCheese);

        totalPrice = PromotionUtils.tooMuch(ingredientsIds, totalPrice, idCheese, cheesePrice);

        return totalPrice;
    }

}
