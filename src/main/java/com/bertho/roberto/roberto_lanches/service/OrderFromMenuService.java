package com.bertho.roberto.roberto_lanches.service;

import com.bertho.roberto.roberto_lanches.util.IngredientUtils;
import com.bertho.roberto.roberto_lanches.entity.Hamburguer;
import com.bertho.roberto.roberto_lanches.entity.Ingredient;
import com.bertho.roberto.roberto_lanches.repository.HamburguerRepository;
import com.bertho.roberto.roberto_lanches.repository.IngredientsRepository;
import com.bertho.roberto.roberto_lanches.util.PromotionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderFromMenuService {

    @Autowired
    private HamburguerRepository hamburguerRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    private Optional<Hamburguer> getById(Integer id) {
        return hamburguerRepository.findById(id);
    }

    public Hamburguer orderHamburguerFromMenu(Integer id, List<Integer> ingredientsIds) {
        Optional<Hamburguer> hamburguerInfo = getById(id);
        Hamburguer hamburguer = hamburguerInfo.orElseThrow(() -> new Error("Hamburguer not found"));

        List<Ingredient> ingredients = new ArrayList();
        ingredientsIds.forEach(e -> ingredients.add(ingredientsRepository.getIngredientsById(e)));

        Map<Ingredient, Integer> qttIngredients = IngredientUtils.getQuantityOfEachIngredient(ingredients);

        Double totalPrice = IngredientUtils.getAllIngredientsPrice(qttIngredients) + hamburguer.getPrice();

        totalPrice = promotionTests(totalPrice, ingredientsIds, hamburguer);

        hamburguer.setPrice(totalPrice);
        return hamburguer;
    }

    private Double promotionTests (Double totalPrice, List<Integer> ingredientsIds, Hamburguer hamburguer) {
        if (!hamburguer.getName().equals("X-BACON") && !hamburguer.getName().equals("X-EGG BACON")) {
            Integer idLettuce = ingredientsRepository.getIngredientIdByName("ALFACE");
            Integer idBacon = ingredientsRepository.getIngredientIdByName("BACON");

            totalPrice = PromotionUtils.light(ingredientsIds, totalPrice, idBacon, idLettuce);
        }

        Integer idMeat = ingredientsRepository.getIngredientIdByName("HAMBURGUER DE CARNE");
        Double meatPrice = ingredientsRepository.getIngredientPrice(idMeat);

        totalPrice = PromotionUtils.tooMuch(ingredientsIds, totalPrice, idMeat, meatPrice);

        Integer idCheese = ingredientsRepository.getIngredientIdByName("QUEIJO");
        Double cheesePrice = ingredientsRepository.getIngredientPrice(idCheese);

        totalPrice = PromotionUtils.tooMuch(ingredientsIds, totalPrice, idCheese, cheesePrice);

        return totalPrice;
    }
}
