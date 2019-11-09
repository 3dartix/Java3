package Lesson_7.dz;

import Lesson_7.dz.annotation.AfterSuite;
import Lesson_7.dz.annotation.BeforeSuite;
import Lesson_7.dz.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //Tests a = new Tests();
        //a.start(DzStudent.class);
        Start(Tests.class);
    }

    public static void Start(Class cl) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //создаем экземпляр класса
        Tests tests = (Tests) cl.newInstance();
        //создаем массив методов
        Method[] methods = cl.getDeclaredMethods();
        List<Method> arrayList = new ArrayList<>();
        //перебираем методы
        for (Method m: methods ) {
            //если у метода есть аннотиция тест
            if(m.isAnnotationPresent(Test.class)) {
                //получаем приоритет метода
                int p = m.getAnnotation(Test.class).priority();
                arrayList.add(m);
            }
        }

        //сортируем методы по приоритету
        Collections.sort(arrayList, new Comparator<Method>() {
            @Override
            public int compare(Method u1, Method u2) {
                return u2.getAnnotation(Test.class).priority() - u1.getAnnotation(Test.class).priority();
            }
        });

        int unique = 0;
        for (Method m: methods) {
            //если у метода есть аннотиция
            if(m.isAnnotationPresent(BeforeSuite.class)) {
                arrayList.add(0,m);
                unique++;
                if(unique > 1){
                    throw new RuntimeException("BeforeSuite должна быть в единственном экземпляре");
                }
            }
        }

        unique = 0;

        for (Method m: methods) {
            //если у метода есть аннотиция
            if(m.isAnnotationPresent(AfterSuite.class)) {
                arrayList.add(m);
                unique++;
                if(unique > 1){
                    throw new RuntimeException("AfterSuite должна быть в единственном экземпляре");
                }
            }
        }


        for (Method m: arrayList) {
            m.invoke(tests);
        }

    }
}
