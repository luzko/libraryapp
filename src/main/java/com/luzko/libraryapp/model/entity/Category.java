package com.luzko.libraryapp.model.entity;

public enum Category {
    FANTASY("category.fantasy"),
    HISTORY("category.history"),
    AUTOBIOGRAPHY("category.autobiography"),
    PHILOSOPHY("category.philosophy"),
    NOVEL("category.novel"),
    PSYCHOLOGY("category.psychology");

    private final String localName;

    Category(String localName) {
        this.localName = localName;
    }

    public String getLocalName() {
        return localName;
    }

    public int defineId() {
        return this.ordinal() + 1;
    }

    public static Category defineCategoryById(int id) {
        for (Category category : Category.values()) {
            if (category.defineId() == id) {
                return category;
            }
        }
        return null;
    }
}
