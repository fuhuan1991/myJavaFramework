package jrails;

public class Html {
    private String text;

    public Html(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }

    public Html empty() {
        return this;
    }

    public Html seq(Html h) {
        return new Html(this.text + h.toString());
    }

    public Html br() {
        return new Html(this.text + "<br/>");
    }

    public Html t(Object o) {
        return new Html(this.text + o.toString());
    }

    public Html p(Html child) {
        String start = "<p>";
        String end = "</p>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html div(Html child) {
        String start = "<div>";
        String end = "</div>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html strong(Html child) {
        String start = "<strong>";
        String end = "</strong>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html h1(Html child) {
        String start = "<h1>";
        String end = "</h1>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html tr(Html child) {
        String start = "<tr>";
        String end = "</tr>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html th(Html child) {
        String start = "<th>";
        String end = "</th>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html td(Html child) {
        String start = "<td>";
        String end = "</td>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html table(Html child) {
        String start = "<table>";
        String end = "</table>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html thead(Html child) {
        String start = "<thead>";
        String end = "</thead>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html tbody(Html child) {
        String start = "<tbody>";
        String end = "</tbody>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html textarea(String name, Html child) {
        String start = "<textarea name=\"" + name + "\">";
        String end = "</textarea>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html link_to(String text, String url) {
        String start = "<a href=\"" + url + "\">";
        String end = "</a>";
        return new Html(this.text + start + text + end);
    }

    public Html form(String action, Html child) {
        String start = "<form action=\"" + action + "\" accept-charset=\"UTF-8\" method=\"post\">";
        String end = "</form>";
        return new Html(this.text + start + child.toString() + end);
    }

    public Html submit(String value) {
        String content = "<input type=\"submit\" value=\"" + value + "\"/>";
        return new Html(this.text + content);
    }
}