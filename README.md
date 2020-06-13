# Spring Boot with GraphQL Query Example

Update: Upgraded to Java 11 and Graph QL to 5+ version dependency

## Book Store
- `/rest/books` is the REST resource which can fetch Books information
- DataFetchers are Interfaces for RuntimeWiring of GraphQL with JpaRepository

## Sample GraphQL Scalar Queries
- Accessible under `http://localhost:8091/rest/books`
- Usage for `allBooks`
```
{
   allBooks {
     isn
     title
     authors
     publisher
   }
 }
```
- Usage for `book`
```
  {
   book(id: "123") {
     title
     authors
     publisher
   }
```
- Combination of both `allBooks` and `book`
```
{
   allBooks {
     title
     authors
   }
   book(id: "124") {
     title
     authors
     publisher
   }
 }
```