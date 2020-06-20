package com.techprimers.graphql.springbootgrapqlexample.service.datafetcher;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.techprimers.graphql.springbootgrapqlexample.model.Book;
import com.techprimers.graphql.springbootgrapqlexample.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddBookFetcher implements DataFetcher<Book> {

    @Autowired
    BookRepository bookRepository;

    public Book addBook(String isn, String title, String publisher) {
        Book book  = new Book();
        book.setIsn(isn);
        book.setTitle(title);
        book.setPublisher(publisher);
        this.bookRepository.save(book);

        return this.bookRepository.getOne(isn);
    }

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        return addBook(
                dataFetchingEnvironment.getArgument("isn"),
                dataFetchingEnvironment.getArgument("title"),
                dataFetchingEnvironment.getArgument("publisher")
        );
    }
}
