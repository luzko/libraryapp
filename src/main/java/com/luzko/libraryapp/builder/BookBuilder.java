package com.luzko.libraryapp.builder;

import com.luzko.libraryapp.model.entity.book.Category;

public class BookBuilder {
    private Long bookId;
    private String title;
    private int year;
    private int pages;
    private String description;
    private int numberCopies;
    private Category category;
    private String authors;

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

    public int getPages() {
        return pages;
    }

    public BookBuilder setPages(int pages) {
        this.pages = pages;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getNumberCopies() {
        return numberCopies;
    }

    public BookBuilder setNumberCopies(int numberCopies) {
        this.numberCopies = numberCopies;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public BookBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getAuthors() {
        return authors;
    }

    public BookBuilder setAuthors(String authors) {
        this.authors = authors;
        return this;
    }
}
