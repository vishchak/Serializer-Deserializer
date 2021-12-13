package com.company;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class Deserializer {
    private static String[] spitFromFile(File file, String regex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(regex);
            return values;
        }
        return null;
    }

    private static void ifSet(Field field, Object obj, String str) throws IllegalAccessException {
        if (field.getType() == Integer.class) {
            field.setInt(obj, Integer.parseInt(str));
        } else if (field.getType() == double.class) {
            field.setDouble(obj, Double.parseDouble(str));
        } else if (field.getType() == String.class) {
            field.set(obj, str);
        } else if (field.getType() == long.class) {
            field.setLong(obj, Long.parseLong(str));
        } else if (field.getType() == char.class) {
            field.set(obj, str.toCharArray());
        } else if (field.getType() == boolean.class) {
            field.setBoolean(obj, Boolean.parseBoolean(str));
        }
    }

    public static <T> T deserialize(File file, Class<T> cls) throws IOException, IllegalAccessException, InstantiationError, InstantiationException, NoSuchFieldException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        T obj = (T) cls.newInstance();

        String[] values = Deserializer.spitFromFile(file, ";");
        for (String res :
                values) {
            String[] result = res.split("=");
            if (result.length == 2) {
                String name = result[0];
                String value = result[1];
                    Field field = cls.getDeclaredField(name);
                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    if (field.isAnnotationPresent(Test.class)) {
                        Deserializer.ifSet(field, obj, value);
                        if (field.getType() == Rebounds.class) {
                            Class<?> innerClass = field.getType();
                            Object rb = innerClass.newInstance();
                            String[] innerValues = value.split(">");
                            for (String finalResult :
                                    innerValues) {
                                String[] finalResults = finalResult.split("<");
                                String finalName = finalResults[0];
                                String finalValue = finalResults[1];
                                Field innerField = innerClass.getDeclaredField(finalName);
                                if (Modifier.isPrivate(innerField.getModifiers())) {
                                    innerField.setAccessible(true);
                                    if (innerField.isAnnotationPresent(Test.class)) {
                                        Deserializer.ifSet(innerField, rb, finalValue);
                                    }
                                    field.set(obj, rb);
                                }
                            }
                        }
                    }

                }
            }
        return obj;
    }
}