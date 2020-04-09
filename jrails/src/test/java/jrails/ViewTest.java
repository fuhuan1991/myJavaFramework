package jrails;

import books.Book;
import books.BookView;
import org.junit.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class ViewTest {

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
  public void empty() {
      assertThat(View.empty().toString(), isEmptyString());
  }

  @Test
  public void show() {
    Book b = Model.find(Book.class,1);
    String result = BookView.show(b).toString();
    String correct = "<p><strong>Title:</strong>The Three-Body Problem</p><p><strong>Author:</strong>Cixin Liu</p><p><strong>Copies:</strong>253</p><a href=\"/edit?id=1\">Edit</a> | <a href=\"/\">Back</a>";
    assertThat(result, is(correct));
  }

  @Test
  public void form() {
    Book b = Model.find(Book.class,1);
    String result = BookView.edit(b).toString();
    String correct = "<h1>Edit Book</h1><form action=\"/update?id=1\" accept-charset=\"UTF-8\" method=\"post\"><div>Title<textarea name=title>The Three-Body Problem</textarea></div><div>Author<textarea name=author>Cixin Liu</textarea></div><div>Copies<textarea name=num_copies>253</textarea></div><div><input type=\"submit\" value=\"Save\"/></div></form>";
    assertThat(result, is(correct));
  }

  @AfterClass
  public static void tearDown() throws Exception {
    Model.reset();
  }
}