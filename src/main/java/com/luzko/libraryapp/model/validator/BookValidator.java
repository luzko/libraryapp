package com.luzko.libraryapp.model.validator;

import com.luzko.libraryapp.model.dao.ColumnName;

import java.util.Map;

/**
 * The type represents the validator for books.
 */
public final class BookValidator {
    private static final int MIN_YEAR = 1000;
    private static final int MAX_YEAR = getCurrentYear();
    private static final int MIN_PAGES = 10;
    private static final int MAX_PAGES = 9999;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 99;
    private static final String EMPTY_VALUE = "";
    private static final String SEARCH_PATTERN = "^[\\p{L} ]{0,25}$";
    private static final String NAME_PATTERN = "^[\\p{L} ]{3,25}$";
    private static final String TITLE_PATTERN = "^[\\p{L} ]{5,25}$";
    private static final String XSS_PATTERN = "(?i)<script.*?>.*?</script.*?>";

    private BookValidator() {

    }

    /**
     * Check valid book parameter.
     *
     * @param bookParameter the book parameter
     * @return the boolean
     */
    public static boolean isValidBookParameter(Map<String, String> bookParameter) {
        boolean isValidParameter = true;
        if (!isValidTitle(bookParameter.get(ColumnName.TITLE))) {
            isValidParameter = false;
            bookParameter.put(ColumnName.TITLE, EMPTY_VALUE);
        }
        if (!isYearValid(Integer.parseInt(bookParameter.get(ColumnName.YEAR)))) {
            isValidParameter = false;
            bookParameter.put(ColumnName.YEAR, EMPTY_VALUE);
        }
        if (!isPagesValid(Integer.parseInt(bookParameter.get(ColumnName.PAGES)))) {
            isValidParameter = false;
            bookParameter.put(ColumnName.PAGES, EMPTY_VALUE);
        }
        if (!isNumberCopiesValid(Integer.parseInt(bookParameter.get(ColumnName.NUMBER_COPIES)))) {
            isValidParameter = false;
            bookParameter.put(ColumnName.NUMBER_COPIES, EMPTY_VALUE);
        }
        if (!isValidDescription(bookParameter.get(ColumnName.DESCRIPTION))) {
            isValidParameter = false;
            bookParameter.put(ColumnName.DESCRIPTION, EMPTY_VALUE);
        }
        return isValidParameter;
    }

    /**
     * Check valid author name.
     *
     * @param name the author name
     * @return the boolean
     */
    public static boolean isValidAuthorName(String name) {
        boolean isNameCorrect = false;
        if (name != null && !name.isEmpty()) {
            isNameCorrect = name.matches(NAME_PATTERN);
        }
        return isNameCorrect;
    }

    /**
     * Check valid book title.
     *
     * @param title the book title
     * @return the boolean
     */
    public static boolean isValidTitle(String title) {
        boolean isTitleCorrect = false;
        if (title != null && !title.isEmpty()) {
            isTitleCorrect = title.matches(TITLE_PATTERN);
        }
        return isTitleCorrect;
    }

    /**
     * Check valid search value.
     *
     * @param searchName the search value
     * @return the boolean
     */
    public static boolean isValidSearchValue(String searchName) {
        boolean isSearchValueCorrect = false;
        if (searchName != null) {
            isSearchValueCorrect = searchName.matches(SEARCH_PATTERN) && !searchName.matches(XSS_PATTERN);
        }
        return isSearchValueCorrect;
    }

    /**
     * Check book year valid.
     *
     * @param year the book year
     * @return the boolean
     */
    public static boolean isYearValid(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    /**
     * Check book pages valid.
     *
     * @param pages the book pages
     * @return the boolean
     */
    public static boolean isPagesValid(int pages) {
        return pages >= MIN_PAGES && pages <= MAX_PAGES;
    }

    private static boolean isValidDescription(String description) {
        boolean isDescriptionCorrect = false;
        if (description != null && !description.isEmpty()) {
            isDescriptionCorrect = !description.matches(XSS_PATTERN);
        }
        return isDescriptionCorrect;
    }

    private static boolean isNumberCopiesValid(int numberCopies) {
        return numberCopies >= MIN_NUMBER && numberCopies <= MAX_NUMBER;
    }

    private static int getCurrentYear() {
        java.util.Calendar calendar = java.util.Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new java.util.Date());
        return calendar.get(java.util.Calendar.YEAR);
    }
}
