package com.techprimers.graphql.springbootgrapqlexample.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.techprimers.graphql.springbootgrapqlexample.model.Book;
import com.techprimers.graphql.springbootgrapqlexample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class CacheService {

    private LoadingCache<String, List<Book>> cache;

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    private void InitCache() throws IOException {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(24, TimeUnit.HOURS)
                .recordStats()
                .build(new CacheLoader<String, List<Book>>() {
                    @Override
                    public List<Book> load(String s) throws IOException {
                        return getBooks();
                    }
                });
    }

    private List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getByIsn(String id) throws ExecutionException {
        return cache.get(id).stream().filter(book -> book.getIsn().equals(id)).findFirst().get();
    }

    public List<Book> getAllBooks() throws ExecutionException {
        return cache.get("");
    }
}
