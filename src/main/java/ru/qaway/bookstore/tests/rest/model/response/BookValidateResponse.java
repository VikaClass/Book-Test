package ru.qaway.bookstore.tests.rest.model.response;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import ru.qaway.bookstore.tests.rest.model.Book;

public class BookValidateResponse {

    private BookResponse model;
    private Response response;

    public BookValidateResponse(Response response) {
        this.response = response;
        if(!response.asString().isEmpty()) {
            model = response.as(BookResponse.class);
        }
    }

    public BookValidateResponse checkStatusCode(int statusCode) {
        response.then().statusCode(statusCode);

        return this;
    }

    public BookValidateResponse checkIdNotNull() {
        response.then().body("id", Matchers.notNullValue());

        return this;
    }

    public BookValidateResponse checkErrorResponse(BookResponse expected){
        response.then().body("timestamp", Matchers.notNullValue());
        Assert.assertEquals(model, expected);

        return this;
    }

    public BookValidateResponse checkLastUpdated() {
        response.then().body("lastUpdated", Matchers.notNullValue());
        return this;
    }

    public BookValidateResponse checkBook(Book expected) {
        Assert.assertEquals(new Book(model), expected);

        return this;
    }

    public BookValidateResponse checkId(Integer id){
    response.then().body("id",Matchers.equalTo(id));
    return this;
    }
    public Integer getId(){
        return response.jsonPath().getInt("id");
    }
}
