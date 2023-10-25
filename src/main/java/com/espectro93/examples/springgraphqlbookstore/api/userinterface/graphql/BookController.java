package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.application.book.ViewBookQuery;
import com.espectro93.examples.springgraphqlbookstore.core.application.book.ViewBooksPagedQuery;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.ViewBook;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.ViewBooksPaged;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookController {

    private final ViewBook viewBook;
    private final ViewBooksPaged viewBooksPaged;

    @QueryMapping
    public BookDto getBook(@Argument String bookId) {
        return BookDto.createFrom(
            viewBook.run(new ViewBookQuery(new BookId(bookId)))
        );
    }

    @QueryMapping
    public Page<BookDto> getBooksPaged(@Argument int page, @Argument int size) {
        return viewBooksPaged
            .run(new ViewBooksPagedQuery(PageRequest.of(page, size)))
            .map(BookDto::createFrom);
    }
}
