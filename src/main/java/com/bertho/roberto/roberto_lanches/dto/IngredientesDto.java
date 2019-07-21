package com.bertho.roberto.roberto_lanches.dto;

import com.bertho.roberto.roberto_lanches.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientesDto {

    private List<Integer> ingredientesId;

    public List<Integer> getIngredientesId() {
        return ingredientesId;
    }

    public void setIngredientesId(List<Integer> ingredientesId) {
        this.ingredientesId = ingredientesId;
    }

    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() { return  ingredients; }
}
