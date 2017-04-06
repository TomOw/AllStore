package com.shoponeo.repository.impl;

import com.shoponeo.model.shop.Category;
import com.shoponeo.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<String> getAllCategories() {
        Query query = entityManager.createQuery("select distinct item.category from Item item");
        List resultList = query.getResultList();
        return resultList;
    }
}
