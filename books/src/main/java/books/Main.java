package books;

import jrails.CsvUtil;
import jrails.JRouter;
import jrails.JServer;
import jrails.Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        JRouter r = new JRouter();

        r.addRoute("GET", "/", BookController.class, "index");
        r.addRoute("GET", "/show", BookController.class, "show");
        r.addRoute("GET", "/new", BookController.class, "new_book");
        r.addRoute("GET", "/edit", BookController.class, "edit");
        r.addRoute("POST", "/create", BookController.class, "create");
        r.addRoute("POST", "/update", BookController.class, "update");
        r.addRoute("GET", "/destroy", BookController.class, "destroy"); // Should be DELETE but no way to do that with a link
        JServer.start(r);
    }
}