package com.company;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMapper<T> {

    private Map<String, String> map = new HashMap<>();
    private Class<T> instanceClass;

    public MyMapper() {
    }

    public T map(Class<T> instanceClass, String params) {
        this.instanceClass = instanceClass;
        T instance = createInstance();
        parseParams(params);


        List<Field> fields = new ArrayList<>();
        getFields(instanceClass, fields);

        for (Field field : fields) {
            String columnName = field.getName();
            if (field.getAnnotation(Column.class) != null) {
                columnName = field.getAnnotation(Column.class).value();
            }

            try {
                if (map.containsKey(columnName)) {
                    Class type = field.getType();
                    if (type == String.class) {
                        String value = map.get(columnName);
                        field.set(instance, value);
                    } else if (type == int.class || type == Integer.class) {
                        Integer value = Integer.parseInt(map.get(columnName));
                        field.set(instance, value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        return instance;
    }

    private static void getFields(Class<?> recordClass, List<Field> fields) {
        if (recordClass.equals(Object.class)) {
            return;
        }
        for (Field field : recordClass.getDeclaredFields()) {
            field.setAccessible(true);
            fields.add(field);
        }
        getFields(recordClass.getSuperclass(), fields);
    }

    private T createInstance() {
        T instance = null;
        try {
            instance = instanceClass.newInstance();
        } catch (Exception e) {
            try {
                UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
                instance = unsafeAllocator.newInstance(instanceClass);
            } catch (Exception e1) {
                System.out.println("Cant create instance");
            }
        }
        return instance;
    }

    private void parseParams(String params) {
        String[] keyValues = params.split(",");
        for (String keyValue : keyValues) {
            String[] tmp = keyValue.trim().split("=");
            map.put(tmp[0].trim(), tmp[1].trim());
        }
    }
}
