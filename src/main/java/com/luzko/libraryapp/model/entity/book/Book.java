package com.luzko.libraryapp.model.entity.book;

import com.luzko.libraryapp.model.builder.BookBuilder;
import com.luzko.libraryapp.model.entity.BaseEntity;

import java.util.StringJoiner;

public class Book extends BaseEntity {
    private Long bookId;
    private String title;
    private int year;
    private int page;
    private String description;
    private int numberCopy;
    private Category category;
    private String author;

    public Book() {

    }

    public Book(BookBuilder builder) {
        this.bookId = builder.getBookId();
        this.title = builder.getTitle();
        this.year = builder.getYear();
        this.page = builder.getPage();
        this.description = builder.getDescription();
        this.numberCopy = builder.getNumberCopy();
        this.category = builder.getCategory();
        this.author = builder.getAuthor();
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberCopy() {
        return numberCopy;
    }

    public void setNumberCopy(int numberCopy) {
        this.numberCopy = numberCopy;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

        if (year != book.year || page != book.page || numberCopy != book.numberCopy || category != book.category) {
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

        return author != null ? author.equals(book.author) : book.author == null;
    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + page;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + numberCopy;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("bookId=" + bookId)
                .add("title='" + title + "'")
                .add("year=" + year)
                .add("pages=" + page)
                .add("description='" + description + "'")
                .add("numberCopies=" + numberCopy)
                .add("category=" + category)
                .add("authors='" + author + "'")
                .toString();
    }
}
