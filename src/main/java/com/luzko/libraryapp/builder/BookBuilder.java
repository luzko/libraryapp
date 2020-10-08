package com.luzko.libraryapp.builder;

import com.luzko.libraryapp.model.entity.book.Category;

public class BookBuilder {
    private Long bookId;
    private String title;
    private int year;
    private int page;
    private String description;
    private int numberCopy;
    private Category category;
    private String author;

    public Long getBookId() {
        return bookId;
    }

    public BookBuilder setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getYear() {
        return year;
    }

    public BookBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public int getPage() {
        return page;
    }

    public BookBuilder setPage(int page) {
        this.page = page;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getNumberCopy() {
        return numberCopy;
    }

    public BookBuilder setNumberCopy(int numberCopy) {
        this.numberCopy = numberCopy;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public BookBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }
}
