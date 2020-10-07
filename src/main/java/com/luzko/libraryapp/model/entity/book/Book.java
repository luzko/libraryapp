package com.luzko.libraryapp.model.entity.book;

import com.luzko.libraryapp.builder.BookBuilder;
import com.luzko.libraryapp.model.entity.BaseEntity;

import java.util.StringJoiner;

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

    public Book(BookBuilder builder) {
        this.bookId = builder.getBookId();
        this.title = builder.getTitle();
        this.year = builder.getYear();
        this.pages = builder.getPages();
        this.description = builder.getDescription();
        this.numberCopies = builder.getNumberCopies();
        this.category = builder.getCategory();
        this.authors = builder.getAuthors();
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book book = (Book) o;

        if (year != book.year || pages != book.pages || numberCopies != book.numberCopies || category != book.category) {
            return false;
        }

        if (bookId != null ? !bookId.equals(book.bookId) : book.bookId != null) {
            return false;
        }

        if (title != null ? !title.equals(book.title) : book.title != null) {
            return false;
        }

        if (description != null ? !description.equals(book.description) : book.description != null) {
            return false;
        }

        return authors != null ? authors.equals(book.authors) : book.authors == null;
    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + pages;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + numberCopies;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("bookId=" + bookId)
                .add("title='" + title + "'")
                .add("year=" + year)
                .add("pages=" + pages)
                .add("description='" + description + "'")
                .add("numberCopies=" + numberCopies)
                .add("category=" + category)
                .add("authors='" + authors + "'")
                .toString();
    }
}
