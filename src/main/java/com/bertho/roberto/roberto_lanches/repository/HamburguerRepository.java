package com.bertho.roberto.roberto_lanches.repository;

import com.bertho.roberto.roberto_lanches.entity.Hamburguer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HamburguerRepository extends JpaRepository<Hamburguer, Integer> {
}
