package com.luzko.libraryapp.model.entity.book;

import com.luzko.libraryapp.model.entity.BaseEntity;

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

    //TODO equals, hashcode, toString..... now fields change.....
}
