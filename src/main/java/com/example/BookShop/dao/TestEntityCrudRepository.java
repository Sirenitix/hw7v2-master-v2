package com.example.BookShop.dao;

import com.example.BookShop.entity.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestEntityCrudRepository extends CrudRepository<TestEntity,Integer> {
}
