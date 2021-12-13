package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class Serializer {
    private static String ifGet(Field field, Object obj, String str) throws IllegalAccessException {
        if (field.getType() == Integer.class) {
            str += field.getInt(obj) + ";";
        } else if (field.getType() == String.class) {
            str += field.get(obj) + ";";
        } else if (field.getType() == double.class) {
            str += field.getDouble(obj) + ";";
        } else if (field.getType() == Long.class) {
            str += field.getLong(obj) + ";";
        } else if (field.getType() == boolean.class) {
            str += field.getBoolean(obj) + ";";
        } else if (field.getType() == char.class) {
            str += field.getChar(obj) + ";";
        }
        return str;
    }

    private static String serialize(Object obj) throws IllegalAccessException {
        String text = "";
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field fld :
                fields) {
            if (fld.isAnnotationPresent(Test.class)) {
                fld.setAccessible(true);
                text += fld.getName() + "=";
                text += Serializer.ifGet(fld, obj, text);
                if (fld.getType() == Rebounds.class) {
                    Object object = fld.get(obj);
                    Class<?> innerObject = fld.getType();
                    Field[] innerFields = innerObject.getDeclaredFields();
                    for (Field innerField :
                            innerFields) {
                        if (innerField.isAnnotationPresent(Test.class)) {
                            innerField.setAccessible(true);
                            if (innerField.getType() == double.class) {

                                text += innerField.getName() + "<" + innerField.getDouble(object) + ">";
                            }
                        }
                    }
                    text += ";";
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

