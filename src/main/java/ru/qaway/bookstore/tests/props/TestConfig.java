package ru.qaway.bookstore.tests.props;

public enum TestConfig {
    uri("uri"),
    path("path");

    public final String value;

    TestConfig(String value){
        this.value = Configuration.getConfiguration().getProperty(value);
    }
}
