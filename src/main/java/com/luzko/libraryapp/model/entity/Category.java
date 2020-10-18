package com.luzko.libraryapp.model.entity;

public enum Category {
    FANTASY(LocaleName.CATEGORY_FANTASY),
    HISTORY(LocaleName.CATEGORY_HISTORY),
    AUTOBIOGRAPHY(LocaleName.CATEGORY_AUTOBIOGRAPHY),
    PHILOSOPHY(LocaleName.CATEGORY_PHILOSOPHY),
    NOVEL(LocaleName.CATEGORY_NOVEL),
    PSYCHOLOGY(LocaleName.CATEGORY_PSYCHOLOGY);

    private final String localeName;

    Category(String localName) {
        this.localeName = localName;
    }

    public String getLocaleName() {
        return localeName;
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
