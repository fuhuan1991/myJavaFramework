package books;

import jrails.Column;
import jrails.Model;

public class Book extends Model {
    @Column
    public String title;

    @Column
    public String author;

    @Column
    public int num_copies;

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", num_copies=" + num_copies +
                '}';
    }
}