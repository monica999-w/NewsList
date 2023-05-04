package com.example.news;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface NewsRepository extends CrudRepository<News,Integer> {
    List<News> findAll(Sort sort);
    List<News> findByTitleContainingOrDescriptionContaining(String keyword, String keyword1);


}
