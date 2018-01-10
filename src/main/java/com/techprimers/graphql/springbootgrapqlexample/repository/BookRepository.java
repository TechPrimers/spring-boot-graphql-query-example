package com.techprimers.graphql.springbootgrapqlexample.repository;

import com.techprimers.graphql.springbootgrapqlexample.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
