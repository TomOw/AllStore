package com.shoponeo.repository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomasz on 15.11.2016.
 */
@Repository
@Transactional
public interface CategoryRepository {

    List<String> getAllCategories();

}
