package jrails;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {

    public int id;

    public Model () {
        this.id = 0;
    }

    public void save() {
        // get the class name of current obj
        String className = this.getClass().getSimpleName();
        String path = "./db/" + className + ".csv";
        // get all the annotated fields
        Field[] fields = this.getClass().getFields();
        ArrayList<String> row = new ArrayList<>();

        for (Field field : fields) {
            if(checkColumn(field)) {
                // check field type
                Class fieldType = field.getType();
                try {
                    if (fieldType.equals(java.lang.String.class)) {
                        row.add((String)field.get(this));
                    } else if (fieldType.equals(int.class)) {
                        row.add(field.get(this).toString());
                    } else if (fieldType.equals(boolean.class)) {
                        row.add(field.get(this).toString());
                    } else {
                        throw new Error("The only possible types for @Column fields are String, int, and boolean!");
                    }
                } catch (Exception e) {
                    throw new Error(e);
                }
            }
        }

        // write to the file
        if (this.id == 0) {
            // create a new row
            int newId = CsvUtil.insert(path, ArrayListToStringArray(row));
            this.id = newId;
        } else {
            // update existing row
            CsvUtil.update(path, id, ArrayListToStringArray(row));
        }
    }

    public int id() {
        return this.id;
    }

    public void setId(int v) {
        this.id = v;
    }

    public static <T> T find(Class<T> c, int id) {
        // get the class name of current obj
        String className = c.getSimpleName();
        String path = "./db/" + className + ".csv";
        String[] row = CsvUtil.query(path, id);
        return createModel(c, row);
    }

    public static <T> List<T> all(Class<T> c) {
        String className = c.getSimpleName();
        String path = "./db/" + className + ".csv";
        ArrayList<String[]> list = CsvUtil.readAll(path);
        ArrayList<T> result = new ArrayList<>();

        for (String[] row : list) {
            result.add(createModel(c, row));
        }
        return result;
    }

    public void destroy(){
        String className = this.getClass().getSimpleName();
        String path = "./db/" + className + ".csv";
        CsvUtil.delete(path, this.id);
    }

    public static void reset() {
        CsvUtil.deleteAll();
    }

    // check if a field has @column
    private static boolean checkColumn(Field field) {
        Annotation[] as = field.getAnnotations();
        for (Annotation anno : as) {
            if (anno.annotationType().equals(jrails.Column.class)) return true;
        }
        return false;
    }

    private static String[] ArrayListToStringArray(ArrayList<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private static <T> T createModel(Class<T> c, String[] row){
        if (row == null) return null;
        // row is a string array got from DB, so row[0] is id
        try {
            T obj =  c.getConstructor().newInstance();
            Field[] fields = c.getFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.getType().equals(java.lang.String.class)) {
                    field.set(obj, row[(i+1)%row.length]);
                } else if (field.getType().equals(int.class)) {
                    field.set(obj, Integer.parseInt(row[(i+1)%row.length]));
                } else {
                    // boolean
                    if (row[(i+1)%row.length].equals("true")) field.set(obj, true);
                    if (row[(i+1)%row.length].equals("false")) field.set(obj, false);
                }
            }
            return obj;
        } catch (Exception e) {
            return null;
        }
    }
}