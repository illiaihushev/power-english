package model;

public class BookExcerpt {
    private int bookExcerptId;
    private int bookId;
    private int chapter;
    private int page;
    private String original;
    private String translation;

    public BookExcerpt(int bookId, int chapter, int page, String original, String translation) {
        this.bookId = bookId;
        this.chapter = chapter;
        this.page = page;
        this.original = original;
        this.translation = translation;
    }

    public BookExcerpt(int bookExcerptId, int bookId, int page, int chapter, String original, String translation) {
        this.bookExcerptId = bookExcerptId;
        this.bookId = bookId;
        this.page = page;
        this.chapter = chapter;
        this.original = original;
        this.translation = translation;
    }

    public int getBookExcerptId() {
        return bookExcerptId;
    }

    public void setBookExcerptId(int bookExcerptId) {
        this.bookExcerptId = bookExcerptId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
