package com.bertho.roberto.roberto_lanches.repository;

import com.bertho.roberto.roberto_lanches.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {
    @Query(nativeQuery = true, value = "SELECT id, price FROM INGREDIENT WHERE id IN (:ingredientsIds)")
    List<Double> getAllIngredientsPriceIn(@Param("ingredientsIds") List<Integer> ingredientsIds);

    @Query(nativeQuery = true, value = "SELECT * FROM INGREDIENT WHERE id IN (:ingredientsIds)")
    List<Ingredient> getAllIngredientsIn(@Param("ingredientsIds") List<Integer> ingredientsIds);

    @Query(nativeQuery = true, value = "SELECT * FROM INGREDIENT WHERE id = (:ingredientId)")
    Ingredient getIngredientsById(@Param("ingredientId") Integer ingredientId);
}
