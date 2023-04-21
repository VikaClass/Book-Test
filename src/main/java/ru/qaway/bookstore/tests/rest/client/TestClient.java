package ru.qaway.bookstore.tests.rest.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import ru.qaway.bookstore.tests.props.TestConfig;
import ru.qaway.bookstore.tests.rest.model.Book;
import ru.qaway.bookstore.tests.rest.model.response.BookValidateResponse;

import static io.restassured.RestAssured.given;

@AllArgsConstructor
public class TestClient {
    private String baseUri;
    private String basePath;

    public TestClient(){
        this(TestConfig.uri.value, TestConfig.path.value);
    }
    public RequestSpecification getRequestSpec(){
        return given().baseUri(baseUri).
                basePath(basePath).
                contentType(ContentType.JSON).
                log().all();
    }
    public RequestSpecification getRequestSpec(Object book){
        return getRequestSpec()
                .body(book);
    }
    public BookValidateResponse create(Book book){
        Response response = getRequestSpec(book).when().
                post("/books");

        response.then().log().all();

        return new BookValidateResponse(response);
    }

    public BookValidateResponse read(Integer id){
        Response response = getRequestSpec().when().
                get("/books/{id}", id);

        response.then().log().all();

        return new BookValidateResponse(response);
    }

    public BookValidateResponse update(Integer id, Book book){
        Response response = getRequestSpec(book).when().
                put("/books/{id}", id);

        response.then().log().all();

        return new BookValidateResponse(response);
    }

    public BookValidateResponse delete(Integer id){
        Response response = getRequestSpec().when().
                delete("/books/{id}", id);

        response.then().log().all();

        return new BookValidateResponse(response);
    }
}
