# Bookstore Sample
This repository shows a sample application for a bookstore backend. The backend is based upon Spring Boot 3 and uses a GraphQL API and a Couchbase DB.

## Prerequisites
- Node and NPM

## Local development
`docker-compose up -d` creates a local couchbase cluster with a bucket.

If you want to test the API locally you can do so by sending POST Requests to `http://localhost:8080/graphql`

### Queries 
<b>GetBook</b>
```json
query {
    getBook(bookId: "your_id") {
      id
    	title
    	authors
    	publishDate
    	pages
    	isbn
    	publisherName
    	inStock
    }
}
```

<b>GetBooksPaged</b>
```json
query {
  getBooksPaged(page: 0, size: 10) {
      id
      title
      authors
      publishDate
      pages
      isbn
      publisherName
      inStock
  }
}
```

### Mutations
 
