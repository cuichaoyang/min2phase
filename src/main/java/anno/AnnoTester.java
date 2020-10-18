package anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * Created by Intellij IDEA.
 *
 * @author cuiguiyang
 * @since 2020/10/18 22:23
 */
@ClassAnno(value = "AnnoTester -- 666")
public class AnnoTester {

    @FieldAnno(value = "name -- 666") private String name;
    @FieldAnno(value = "age -- 666") private Integer age;

    public AnnoTester(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @MethodAnno("testAnno -- 666")
    public void testAnno(@ParamAnno("param name -- 666") String name, @ParamAnno("param age -- 666") Integer age) {
        System.out.printf("Parameter:{name:%s, age:%d}\n", name, age);
    }

    public static void main(String[] args) throws Exception {
        AnnoTester annoTester = new AnnoTester("zhangsan", 22);

        Class<AnnoTester> clazz = AnnoTester.class;
        ClassAnno classAnno = clazz.getAnnotation(ClassAnno.class);
        System.out.println("classAnno = " + classAnno);

        Field nameField = clazz.getDeclaredField("name");
        FieldAnno nameFieldAnno = nameField.getAnnotation(FieldAnno.class);
        System.out.println("nameFieldAnno = " + nameFieldAnno);

        Field ageField = clazz.getDeclaredField("age");
        FieldAnno ageFieldAnno = ageField.getAnnotation(FieldAnno.class);
        System.out.println("ageFieldAnno = " + ageFieldAnno);

        Method testAnnoMethod = clazz.getMethod("testAnno", String.class, Integer.class);
        MethodAnno testAnnoMethodAnno = testAnnoMethod.getAnnotation(MethodAnno.class);
        System.out.println("testAnnoMethodAnno = " + testAnnoMethodAnno);
        Annotation[][] parameterAnnotations = testAnnoMethod.getParameterAnnotations();
        System.out.println("parameterAnnotations = " + Arrays.deepToString(parameterAnnotations));
        int parameterCount = testAnnoMethod.getParameterCount();
        System.out.println("parameterCount = " + parameterCount);
        Parameter[] parameters = testAnnoMethod.getParameters();
        System.out.println("parameters = " + Arrays.toString(parameters));
    }

}
