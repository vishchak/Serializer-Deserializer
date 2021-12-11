package com.company;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class Deserializer {
    public static <T> T deserialize(File file, Class<T> cls) throws IOException, IllegalAccessException, InstantiationError, InstantiationException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        T obj = (T) cls.newInstance();

        while ((line = br.readLine()) != null) {
            String[] values = line.split(";");
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
                            }
                        }
                    } catch (NoSuchFieldException e) {
                        System.out.println("No data");
                    }
                }
            }
        }
        System.out.println(obj);
        return obj;
    }
}