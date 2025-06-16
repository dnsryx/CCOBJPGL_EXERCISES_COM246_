public abstract class Book {
    private String title;
    private String author;
    private String date;
    private String abstractText;

    public Book(String title, String author, String date, String abstractText) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.abstractText = abstractText;
    }
    public String getTitle() {
        return title; }
    public String getAuthor() {
        return author; }
    public String getDate() {
        return date; }
    public String getAbstractText() {
        return abstractText; }
    }