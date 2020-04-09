package jrails;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JRouter {

    private HashMap<String, Record> store;

    public JRouter () {
        this.store = new HashMap<String, Record>();
    }

    public void addRoute(String verb, String path, Class clazz, String method) {
        this.store.put(verb + "[]" + path, new Record(clazz, method));
    }

    // Returns "clazz#method" corresponding to verb+URN
    // Null if no such route
    public String getRoute(String verb, String path) {
        Record o = this.store.get(verb + "[]" + path);
        if (o == null) return null;
        return o.klass.getSimpleName() + "#" + o.method;
    }

    // Call the appropriate controller method and
    // return the result
    public Html route(String verb, String path, Map<String, String> params) {
        Record record = this.store.get(verb + "[]" + path);
        if (record == null) throw new UnsupportedOperationException();
        Class klass = record.klass;
        String methodName = record.method;
        try {
            // The input to all controller methods is a hash of parameter names to values,
            // both of which are strings
            Method method = klass.getMethod(methodName, Map.class);
            Object obj = klass.getConstructor().newInstance();
            return (Html) method.invoke(obj, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Html("Error");
    }

    public void show () {
        System.out.println(this.store);
    }

    class Record {
        Class klass;
        String method;

        public Record(Class klass, String method) {
            this.klass = klass;
            this.method = method;
        }
    }
}

