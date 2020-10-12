package com.luzko.libraryapp.model.entity;

public enum Category {
    FANTASY,
    HISTORY,
    AUTOBIOGRAPHY,
    PHILOSOPHY,
    NOVEL,
    PSYCHOLOGY;

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
