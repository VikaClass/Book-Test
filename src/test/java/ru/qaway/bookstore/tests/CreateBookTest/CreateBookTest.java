package ru.qaway.bookstore.tests.CreateBookTest;

import org.testng.annotations.Test;
import ru.qaway.bookstore.tests.BookData;
import ru.qaway.bookstore.tests.BookStoreTestBase;
import ru.qaway.bookstore.tests.rest.model.Book;
import ru.qaway.bookstore.tests.rest.model.response.BookValidateResponse;

public class CreateBookTest extends BookStoreTestBase {

    @Test(dataProvider = "positive", dataProviderClass = BookData.class)
    public void testCreateBook(Book book) {
        BookValidateResponse response = testClient.create(book).
                checkStatusCode(201).
                checkIdNotNull().
                checkLastUpdated().
                checkBook(book);

        testClient.read(response.getId()).
                checkStatusCode(200).
                checkId(response.getId()).
                checkLastUpdated().
                checkBook(book);
    }

}
