package com.luzko.libraryapp.model.entity.book;

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

    public static Category defineRoleById(int id) {
        for (Category category : Category.values()) {
            if (category.defineId() == id) {
                return category;
            }
        }
        return null;
    }
}
