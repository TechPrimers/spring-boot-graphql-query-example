package com.techprimers.graphql.springbootgrapqlexample.service.datafetcher;

import com.techprimers.graphql.springbootgrapqlexample.model.Book;
import com.techprimers.graphql.springbootgrapqlexample.repository.BookRepository;
import com.techprimers.graphql.springbootgrapqlexample.service.CacheService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>>{

    @Autowired
    CacheService cacheService;

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
        try {
            return cacheService.getAllBooks();
        } catch (ExecutionException e) {
            return new ArrayList<>();
        }
    }
}
