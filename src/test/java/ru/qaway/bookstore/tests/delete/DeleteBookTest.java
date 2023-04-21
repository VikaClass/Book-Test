package ru.qaway.bookstore.tests.delete;

import org.testng.annotations.Test;
import ru.qaway.bookstore.tests.BookStoreTestBase;
import ru.qaway.bookstore.tests.rest.model.Book;
import ru.qaway.bookstore.tests.rest.model.response.BookValidateResponse;

public class DeleteBookTest extends BookStoreTestBase {

    @Test
    public void testDeleteBook() {
        BookValidateResponse response = testClient.create(Book.defaultOf())
                .checkStatusCode(201);

        testClient.delete(response.getId())
                .checkStatusCode(200);

        testClient.read(response.getId())
                .checkStatusCode(404);
    }

    @Test
    public void testDeleteNotExistedBook() {
        testClient.delete(999999999)
                .checkStatusCode(404);

    }
}
