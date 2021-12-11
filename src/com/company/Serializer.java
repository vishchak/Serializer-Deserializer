package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public class Serializer {
    private static String serialize(Object obj) throws IllegalAccessException {
        String text = "";
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field fld :
                fields) {
            if (fld.isAnnotationPresent(Test.class)) {
                fld.setAccessible(true);
                text += fld.getName() + "=";
                if (fld.getType() == Integer.class) {
                    text += fld.getInt(obj) + ";";
                } else if (fld.getType() == String.class) {
                    text += (String) fld.get(obj) + ";";
                } else if (fld.getType() == double.class) {
                    text += fld.getDouble(obj) + ";";
                }
            }
        }
        return text;
    }

    public static void saveSerialized(Object obj, String file) throws IllegalAccessException, FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        pw.println(Serializer.serialize(obj));
        pw.close();
    }
}
