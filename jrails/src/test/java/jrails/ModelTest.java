package jrails;

import books.Book;
import org.junit.*;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ModelTest {

    @BeforeClass
    public static void setUp() throws Exception {
        Model.reset();
        Book b1 = new Book();
        Book b2 = new Book();
        Book b3 = new Book();
        Book b4 = new Book();

        b1.author = "Cixin Liu";
        b1.title = "The Three-Body Problem";
        b1.num_copies = 253;
        b1.save();

        b2.author = "Paolo Bacigalupi";
        b2.title = "The Windup Girl";
        b2.num_copies = 326;
        b2.save();

        b3.author = "Dan Simmons";
        b3.title = "Hyperion";
        b3.num_copies = 734;
        b3.save();

        b4.author = "Robert A. Heinlein";
        b4.title = "Starship Troopers";
        b4.num_copies = 992;
        b4.save();
    }

    @Test
    public void initial_id() {
        assertThat(new Book().id(), is(0));
    }

    @Test
    public void find_books() {
//        assertThat(Model.all(Book.class).size(), is(4));
        assertThat(Model.find(Book.class, 3).title, is("Hyperion"));
    }

    @Test
    public void sale_a_book() {
        Book b = Model.find(Book.class, 4);
        int counter = b.num_copies;
        b.num_copies--;
        b.save();
        Book bb = Model.find(Book.class, 4);
        assertThat(bb.num_copies, is(counter-1));
    }

    @Test
    public void m2() {
        Book b1 = new Book();
        b1.author = "Cixin Liu";
        b1.title = "The Three-Body Problem";
        b1.num_copies = 253;
        b1.save();
        assertNotEquals(b1.id(), 0);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Model.reset();
    }
}
