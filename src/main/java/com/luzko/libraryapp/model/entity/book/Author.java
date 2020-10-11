package com.luzko.libraryapp.model.entity.book;

import com.luzko.libraryapp.model.entity.BaseEntity;

import java.util.StringJoiner;

public class Author extends BaseEntity {
    private long authorId;
    private String name;

    public Author() {

    }

    public Author(Long authorId, String name) {
        this.authorId = authorId;
        this.name = name;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Author author = (Author) o;

        if (authorId != author.authorId) {
            return false;
        }
        return name != null ? name.equals(author.name) : author.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (authorId ^ (authorId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Author.class.getSimpleName() + "[", "]")
                .add("authorId=" + authorId)
                .add("name='" + name + "'")
                .toString();
    }
}
