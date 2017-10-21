# PhoenixFramework
**Phoenix** is a Java framework which represents a simple and transparent way to work with database. 
The main concept of the framework is mapping data from a query to data object (Pattern [DTO](https://en.wikipedia.org/wiki/Data_transfer_object)) 
which is a simple [POJO](https://en.wikipedia.org/wiki/Plain_old_Java_object). At the moment, the main active object is the **SessionFactory**.

## Concept of SessionFactory ##
**SessionFactory** represents the basic mechanism for getting a **Session** object. To work correctly 
you must register and configure [DataSource](https://docs.oracle.com/javase/7/docs/api/javax/sql/DataSource.html).
```java
SessionFactory sessionFactory = SessionFactory.instance();
sessionFactory.registerDataSource(new DataSourceSupplier() {
    @Override
    public DataSource getDataSource() {
        DataSource dataSource = ...;
        // DataSource configuration
        return dataSource;
    }
});
```

## Concept of Session ##
**Session** represents the physical connection between Java application and database. Session is a lightweight object, so it is always 
created (`open session`) when you need to execute a query. After working with the session it must be destroyed (`close session`). 
Right way to work with session is to wrap it in `try-with-resources` because then there is no need to worry about its closure.

**Session** is a wrapper over a [java.sql.Connection](https://docs.oracle.com/javase/7/docs/api/java/sql/Connection.html) with additional sugar.

To work with a **Session** it is enough to get instance from **SessionFactory**:
```java
try (Session session = SessionFactory.instance().openSession()) {
    // Working with the session
}
```

A typical transaction should use the following idiom:
```java
try (Session session = SessionFactory.instance().openSession()) {
    Transaction tx = session.beginTransaction();
    try {
        // Working with transaction in the session
        tx.commit();
    } catch (Exception e) {
        tx.rollback();
    }
}
```

## Concept of ResultMapper ##
**ResultMapper** allows to transform data from a database to specific data object. You can determine a custom ResultMapper 
which describes the transformation logic.

```java
ResultMapper<MovieDto> movieMapper = new CustomResultMapper<MovieDto>() {
    @Override
    protected MovieDto map(ReadOnlyScrollableResult scrollableResult) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(scrollableResult.getInt("id"));
        movieDto.setOriginalName(scrollableResult.getString("original_name"));
        movieDto.setYear(scrollableResult.getInt("year"));
        movieDto.setDuration(scrollableResult.getInt("duration"));
        return movieDto;
    }
};
```
But you can also use **AliasResultMapper**, which with the help of `Reflection API` will make the transformation itself.
```java
ResultMapper<MovieDto> movieMapper = new AliasResultMapper<>(MovieDto.class);
```

## Example ##
Suppose there is a database schema:
```sql
CREATE SCHEMA movie_management;
CREATE TABLE movie_management.movies(
    id INTEGER NOT NULL,
    original_name CHARACTER VARYING(120) NOT NULL,
    "year" SMALLINT NOT NULL,
    rating REAL DEFAULT 0.0,
    votes_count BIGINT DEFAULT 0,
    duration SMALLINT DEFAULT 0,
    last_update DATE NOT NULL,
    PRIMARY KEY (id)
);
```
and we need to get the following data: ***id, original_name, year, rating***

Create domain object:
```java
@Domain
public class MovieDto {
    private int id;
    @FromColumn("original_name")
    private String originalName;
    private int year;
    private double rating;
    
    // default constructor and getters/setters
}
```
Annotation ```@Domain``` allows to mark a class as domain object.
Annotation ```@FromColumn``` allows to specify a column name if it does not match the field name.

```java
ResultMapper<MovieDto> movieMapper = new AliasResultMapper<MovieDto>();

try (Session session = SessionFactory.instance().openSession()) {
    CachedScrollableResult result = session
            .createQuery("SELECT id, original_name, \"year\", rating " +
                         "FROM movie_management.movies " +
                         "WHERE id = ?")
            .setInt(1, 1)
            .execute();
    MovieDto movie = movieMapper.unique(result);
    // Work with movie object

    CachedScrollableResult result = session.createQuery(
            "SELECT id, original_name, \"year\", rating " +
            "FROM movie_management.movies")
            .execute();
    List<MovieDto> movies = movieMapper.list(result);
    // Work with list of movies
}
```
