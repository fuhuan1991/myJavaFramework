package jrails;

public class View {
    public static Html empty() {
        return new Html("");
    }

    public static Html br() {
        return new Html("<br/>");
    }

    public static Html t(Object o) {
        return new Html(o.toString());
    }

    public static Html p(Html child) {
        String start = "<p>";
        String end = "</p>";
        return new Html(start + child.toString() + end);
    }

    public static Html div(Html child) {
        String start = "<div>";
        String end = "</div>";
        return new Html(start + child.toString() + end);
    }

    public static Html strong(Html child) {
        String start = "<strong>";
        String end = "</strong>";
        return new Html(start + child.toString() + end);
    }

    public static Html h1(Html child) {
        String start = "<h1>";
        String end = "</h1>";
        return new Html(start + child.toString() + end);
    }

    public static Html tr(Html child) {
        String start = "<tr>";
        String end = "</tr>";
        return new Html(start + child.toString() + end);
    }

    public static Html th(Html child) {
        String start = "<th>";
        String end = "</th>";
        return new Html(start + child.toString() + end);
    }

    public static Html td(Html child) {
        String start = "<td>";
        String end = "</td>";
        return new Html(start + child.toString() + end);
    }

    public static Html table(Html child) {
        String start = "<table>";
        String end = "</table>";
        return new Html(start + child.toString() + end);
    }

    public static Html thead(Html child) {
        String start = "<thead>";
        String end = "</thead>";
        return new Html(start + child.toString() + end);
    }

    public static Html tbody(Html child) {
        String start = "<tbody>";
        String end = "</tbody>";
        return new Html(start + child.toString() + end);
    }

    public static Html textarea(String name, Html child) {
        String start = "<textarea name=" + name + ">";
        String end = "</textarea>";
        return new Html(start + child.toString() + end);
    }

    public static Html link_to(String text, String url) {
        String start = "<a href=\"" + url + "\">";
        String end = "</a>";
        return new Html(start + text + end);
    }

    public static Html form(String action, Html child) {
        String start = "<form action=\"" + action + "\" accept-charset=\"UTF-8\" method=\"post\">";
        String end = "</form>";
        return new Html(start + child.toString() + end);
    }

    public static Html submit(String value) {
        String content = "<input type=\"submit\" value=\"" + value + "\"/>";
        return new Html(content);
    }
}