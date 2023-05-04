package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.ports.in.GetBook;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.GetBooks;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.OrderBooks;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookController {
    private final GetBook getBook;
    private final GetBooks getBooks;
    private final OrderBooks orderBooks;

    @QueryMapping
    public String getBook(@Argument String bookId) {
        return "";
    }

    @QueryMapping
    public String getBooks() {
        return "";
    }

    @MutationMapping
    public String orderBooks(@Argument List<String> bookIds) {
        return "";
    }

}
