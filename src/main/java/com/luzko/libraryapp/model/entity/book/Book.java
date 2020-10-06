package com.luzko.libraryapp.model.entity.book;

import com.luzko.libraryapp.model.entity.BaseEntity;

public class Book extends BaseEntity {
    private Long bookId;
    private String title;
    private int year;
    private int pages;
    private String description;
    private int numberCopies;
    private Category category;
    private String authors;

    public Book() {

    }

    public Book(Long bookId, String title, int year, int pages, String description, int numberCopies, Category category, String authors) {
        this.bookId = bookId;
        this.title = title;
        this.year = year;
        this.pages = pages;
        this.description = description;
        this.numberCopies = numberCopies;
        this.category = category;
        this.authors = authors;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberCopies() {
        return numberCopies;
    }

    public void setNumberCopies(int numberCopies) {
        this.numberCopies = numberCopies;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    //TODO equals, hashcode, toString..... now fields change.....
}
