package com.example.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired private NewsRepository repo;
    public List<News> listAll(String sort) {
        String[] sortParams = sort.split("_");
        Sort.Direction sortDirection = sortParams[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        if (sortParams[0].equals("title")) {
            return repo.findAll(Sort.by(sortDirection, "title"));
        } else {
            return repo.findAll(Sort.by(sortDirection, "publicationDate"));
        }
    }
    public void save(News news) {
        repo.save(news);
    }

    public List<News> searchByKeyword(String keyword) {
        return repo.findByTitleContainingOrDescriptionContaining(keyword, keyword);

    }

}
