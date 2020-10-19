package com.luzko.libraryapp.validator;

import com.luzko.libraryapp.model.dao.ColumnName;

import java.util.Map;

public final class BookValidator {
    private static final int minYear = 1000;
    private static final int maxYear = getCurrentYear();
    private static final int minPages = 10;
    private static final int maxPages = 9999;
    private static final int minNumber = 1;
    private static final int maxNumber = 99;
    private static final String EMPTY_VALUE = "";
    private static final String NAME_PATTERN = "^[\\p{L} ]{3,25}$";
    private static final String TITLE_PATTERN = "^[\\p{L} ]{5,25}$";
    private static final String XSS_PATTERN = "(?i)<script.*?>.*?</script.*?>";

    private BookValidator() {

    }

    public static boolean isValidBookParameter(Map<String, String> bookParameter) {
        boolean isValidParameter = true;
        if (!isValidTitle(bookParameter.get(ColumnName.TITLE))) {
            System.out.println(11);
            isValidParameter = false;
            bookParameter.put(ColumnName.TITLE, EMPTY_VALUE);
        }
        if (!isYearValid(Integer.parseInt(bookParameter.get(ColumnName.YEAR)))) {
            isValidParameter = false;
            bookParameter.put(ColumnName.YEAR, EMPTY_VALUE);
        }
        if (!isPagesValid(Integer.parseInt(bookParameter.get(ColumnName.PAGES)))) {
            System.out.println(22);
            isValidParameter = false;
            bookParameter.put(ColumnName.PAGES, EMPTY_VALUE);
        }
        if (!isNumberCopiesValid(Integer.parseInt(bookParameter.get(ColumnName.NUMBER_COPIES)))) {
            System.out.println(33);
            isValidParameter = false;
            bookParameter.put(ColumnName.NUMBER_COPIES, EMPTY_VALUE);
        }
        if (!isValidDescription(bookParameter.get(ColumnName.DESCRIPTION))) {
            System.out.println(44);
            isValidParameter = false;
            bookParameter.put(ColumnName.DESCRIPTION, EMPTY_VALUE);
        }
        return isValidParameter;
    }

    public static boolean isValidAuthorName(String name) {
        boolean isNameCorrect = false;
        if (name != null && !name.isEmpty()) {
            isNameCorrect = name.matches(NAME_PATTERN);
        }
        return isNameCorrect;
    }

    public static boolean isValidTitle(String title) {
        boolean isTitleCorrect = false;
        if (title != null && !title.isEmpty()) {
            isTitleCorrect = title.matches(TITLE_PATTERN);
        }
        return isTitleCorrect;
    }

    private static boolean isValidDescription(String description) {
        boolean isDescriptionCorrect = false;
        if (description != null && !description.isEmpty()) {
            isDescriptionCorrect = !description.matches(XSS_PATTERN);
        }
        return isDescriptionCorrect;
    }

    public static boolean isYearValid(int year) {
        return year >= minYear && year <= maxYear;
    }

    public static boolean isPagesValid(int pages) {
        return pages >= minPages && pages <= maxPages;
    }

    private static boolean isNumberCopiesValid(int numberCopies) {
        return numberCopies >= minNumber && numberCopies <= maxNumber;
    }

    private static int getCurrentYear() {
        java.util.Calendar calendar = java.util.Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new java.util.Date());
        return calendar.get(java.util.Calendar.YEAR);
    }
}
