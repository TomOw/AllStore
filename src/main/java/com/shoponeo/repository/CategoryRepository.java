package com.shoponeo.repository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoryRepository {

    List<String> getAllCategories();

}
