package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import java.util.List;

public record AddBookToCatalogDto(String title,
                                  List<String> authors,
                                  String publishDate,
                                  int pages,
                                  String isbn,
                                  String publisherName,
                                  int stock) {
}
