package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.application.GetBookCommand;
import com.espectro93.examples.springgraphqlbookstore.core.application.GetBooksPagedCommand;
import com.espectro93.examples.springgraphqlbookstore.core.domain.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.GetBook;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.GetBooksPaged;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.OrderBooks;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookController {

    private final GetBook getBook;
    private final GetBooksPaged getBooksPaged;
    private final OrderBooks orderBooks;

    @QueryMapping
    public BookDto getBook(@Argument String bookId) {
        return BookDto.createFrom(
            getBook.run(new GetBookCommand(new BookId(bookId)))
        );
    }

    @QueryMapping
    public Page<BookDto> getBooksPaged(@Argument int page, @Argument int size) {
        return getBooksPaged
            .run(new GetBooksPagedCommand(PageRequest.of(page, size)))
            .map(BookDto::createFrom);
    }

    @MutationMapping
    public String orderBooks(@Argument List<String> bookIds) {
        return "";
    }
}
