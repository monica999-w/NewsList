package com.example;

import com.example.news.News;
import com.example.news.NewsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class NewsRepositoryTests {
    @Autowired private NewsRepository repo;
    @Test
    public void testAddNews(){
        News news = new News();
        news.setTitle("titlu1");
        news.setLink("link");
        news.setDescription("descriere1");
        news.setPublicationDate(LocalDate.of(2023, 11, 22));

       News saveNews = repo.save(news);


        if (saveNews.getId() <= 0) {
            throw new AssertionError("ID should be greater than 0");
        }
    }
    @Test
    public void testListAll(){
        Iterable<News> news = repo.findAll();

        for (News news1 : news){
            System.out.println(news1);
        }
    }


}
