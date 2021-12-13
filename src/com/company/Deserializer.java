package com.company;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class Deserializer {
    public static String[] spitFromFile(File file, String regex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(regex);
            return values;
        }
        return null;
    }


    public static <T> T deserialize(File file, Class<T> cls) throws IOException, IllegalAccessException, InstantiationError, InstantiationException {
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
                try {
                    Field field = cls.getDeclaredField(name);
                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    if (field.isAnnotationPresent(Test.class)) {
                        if (field.getType() == Integer.class) {
                            field.setInt(obj, Integer.parseInt(value));
                        } else if (field.getType() == double.class) {
                            field.setDouble(obj, Double.parseDouble(value));
                        } else if (field.getType() == String.class) {
                            field.set(obj, value);
                        } else if (field.getType() == Rebounds.class) {
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
                                        if (innerField.getType() == double.class) {
                                            innerField.setDouble(rb, Double.parseDouble(finalValue));
                                        }
                                        field.set(obj, rb);
                                    }
                                }
                            }
                        }
                    }
                } catch (NoSuchFieldException e) {
                    System.out.println("No data");
                }
            }
        }
        System.out.println(obj);
        return obj;
    }
}