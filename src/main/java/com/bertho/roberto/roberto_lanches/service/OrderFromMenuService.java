package com.bertho.roberto.roberto_lanches.service;

import com.bertho.roberto.roberto_lanches.util.IngredientUtils;
import com.bertho.roberto.roberto_lanches.entity.Hamburguer;
import com.bertho.roberto.roberto_lanches.entity.Ingredient;
import com.bertho.roberto.roberto_lanches.repository.HamburguerRepository;
import com.bertho.roberto.roberto_lanches.repository.IngredientsRepository;
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
        // return hamburguerId.orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public Hamburguer orderHamburguerFromMenu(Integer id, List<Integer> ingredientsIds) {
        Optional<Hamburguer> hamburguerInfo = getById(id);
        Hamburguer hamburguer = hamburguerInfo.orElseThrow(() -> new Error("Hamburguer not found"));

        List<Ingredient> ingredients = new ArrayList();
        ingredientsIds.forEach(e -> ingredients.add(ingredientsRepository.getIngredientsById(e)));

        Map<Ingredient, Integer> qttIngredients = IngredientUtils.getQuantityOfEachIngredient(ingredients);

        Double totalIngredients = IngredientUtils.getAllIngredientsPrice(qttIngredients);

        // List<Double> ingredients = ingredientsRepository.getAllIngredientsPriceIn(ingredientsIds);
        // Double totalIngredients = ingredients.stream().reduce(0.0, (subtotal, element) -> subtotal + element);

        hamburguer.setPrice(hamburguer.getPrice() + totalIngredients);
        return hamburguer;
    }
}
