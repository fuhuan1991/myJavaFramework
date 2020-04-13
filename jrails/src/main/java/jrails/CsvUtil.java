package jrails;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

public class CsvUtil {

  // read all
  public static ArrayList<String[]> readAll(String path) {
    ArrayList<String[]> result = new ArrayList<>();
    try {

      // create directory if needed
      File dir = new File("./db");
      if (!dir.exists()) dir.mkdirs();

      // create a new file if needed
      File file = new File(path);
      if (!file.exists()) {
        FileWriter fw = new FileWriter(file);
        fw.close();
      }
      Scanner x = new Scanner(file);
      while (x.hasNext()) {
        String line = x.nextLine();
        String[] row = line.split(",");
        returnComma(row);
        result.add(row);
      }
    } catch (Exception e) {
      throw new Error(e);
    }
    return result;
  }

  // update
  public static boolean update(String path, int id, String[] newContent) {

    if (id == 0) {
      throw new Error("cannot update non-existing record!");
    }

    ArrayList<String[]> result = readAll(path);
    boolean targetFound = false;

    // get all elements and change the target
    for (int i = 0; i <= result.size()-1; i++) {
      String[] row = result.get(i);
      int currentId = Integer.parseInt(row[0]);

      if (currentId == id) {
        // target found, modify it
        newContent = addId(row[0], newContent);
        result.set(i, newContent);
        targetFound = true;
        break;
      } else {
        // check next one
        continue;
      }
    }

    if (!targetFound) {
      throw new Error("this id doesn't exist!");
    }

    // write them back into the file
    return writeListToFile(path, result);
  }

  // query
  public static String[] query(String path, int id) {
    ArrayList<String[]> result = readAll(path);
    for (String[] row : result) {
      if (Integer.parseInt(row[0]) == id) {
        returnComma(row);
        // target found
        return row;
      } else {
        // check next one
        continue;
      }
    }
    return null;
  }

  // delete
  public static boolean delete (String path, int id){
    ArrayList<String[]> result = readAll(path);
    ArrayList<String[]> newResult = new ArrayList<>();
    boolean targetFound = false;

    for (String[] row : result) {
      if (Integer.parseInt(row[0]) == id) {
        // target found, so do nothing
        targetFound = true;
        continue;
      } else {
        // copy
        newResult.add(row);
      }
    }

    if (!targetFound) {
//      throw new Exception("this id doesn't exist!");
      throw new RuntimeException("this id doesn't exist!");
    }

    // write them back into the file
    return writeListToFile(path, newResult);
  }

  // insert
  public static int insert(String path, String[] content) {
    ArrayList<String[]> result = readAll(path);
    int maxId = 0;
    // figure out the id for new record
    for (String[] row : result) {
      maxId = Math.max(maxId, Integer.parseInt(row[0]));
    }
    int nextId = maxId+1;

    result.add(addId(Integer.toString(nextId), content));
    // write them back into the file
    writeListToFile(path, result);
    return nextId;
  }

  // deleteAll
  public static void deleteAll() {
    File dir = new File("./db/");
    if (!dir.exists()) dir.mkdirs();

    try {
      for (File file: dir.listFiles()) {
        FileWriter fwOb = new FileWriter(file);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
      }
    } catch (Exception e) {
      throw new Error(e);
    }
  }

  private static boolean writeListToFile(String path, ArrayList<String[]> list) {
    try {
      FileWriter csvWriter = new FileWriter(path);
      for (String[] row : list) {
        replaceComma(row);
        csvWriter.append(String.join(",", row));
        csvWriter.append("\n");
      }
      csvWriter.flush();
      csvWriter.close();
    } catch (Exception e) {
      throw new Error(e);
    }
    return true;
  }

  public static String[] addId(String id, String[] arr) {
    String[] newArr = new String[arr.length+1];
    newArr[0] = id;
    for (int i = 1; i <= newArr.length-1; i++) {
      newArr[i] = arr[i-1];
    }
    return newArr;
  }

  public static void replaceComma(String[] row) {
    for (int i = 0; i < row.length; i++) {
      row[i] = row[i] == null? "null" : row[i].replaceAll(",", "#44");
    }
  }

  public static void returnComma(String[] row) {
    for (int i = 0; i < row.length; i++) {
      row[i] = row[i] == "null"? null : row[i].replaceAll("#44", ",");
    }
  }
  
}
