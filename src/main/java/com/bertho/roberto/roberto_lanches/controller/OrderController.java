package com.bertho.roberto.roberto_lanches.controller;

import com.bertho.roberto.roberto_lanches.dto.IngredientesDto;
import com.bertho.roberto.roberto_lanches.entity.Hamburguer;
import com.bertho.roberto.roberto_lanches.entity.Ingredient;
import com.bertho.roberto.roberto_lanches.service.OrderCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bertho.roberto.roberto_lanches.service.OrderFromMenuService;

import java.util.List;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {


    @Autowired
    private OrderFromMenuService orderFromMenuService;

    @Autowired
    private OrderCustomService orderCustomService;

    @PostMapping("/{id}")
    // public ResponseEntity<IngredientesDto> OrderFromMenu(@PathVariable Integer id, @RequestBody IngredientesDto body)
    public ResponseEntity<Hamburguer> OrderFromMenu(@PathVariable Integer id, @RequestBody IngredientesDto body) {
        List<Integer> ingredientesId = body.getIngredientesId();
        try {
            Hamburguer result = orderFromMenuService.orderHamburguerFromMenu(id, ingredientesId);
            return ResponseEntity.ok(result);
        } catch (Error e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/custom")
    public ResponseEntity<Double> OrderCustom(@RequestBody IngredientesDto body) {
        List<Integer> ingredientsIds = body.getIngredientesId();

        Double result = orderCustomService.OrderCustom(ingredientsIds);
        return ResponseEntity.ok(result);
    }
}

