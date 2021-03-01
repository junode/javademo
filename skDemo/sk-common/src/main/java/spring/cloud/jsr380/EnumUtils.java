package spring.cloud.jsr380;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.EnumSet;

/**
 * @Description 枚举工具类
 * @Author junode
 * @Date 2021/1/25
 */
public class EnumUtils {

    public static boolean valueIn(Object value,Class<? extends Enum> enumType) {
        return from(enumType,value) != null;
    }

    public static boolean valueIn(Object value ,Enum... values) {
        Enum getValueEnumType = from(values[0].getClass(), value);
        for(Enum val:values) {
            if(val.equals(getValueEnumType)) {
                return true;
            }
        }
        return false;
    }

    public static <E extends Enum<E>> E from(Class<E> enumType, Object value) {
        String methodName = getMethodIdentifier(enumType);
        return from(enumType, value, methodName);
    }



    public static <E extends Enum<E>> E from(Class<E> enumType, Object value, String methodName) {
        EnumSet<E> enumSet = EnumSet.allOf(enumType);
        for (E en : enumSet) {
            try {
                String invoke = enumType.getMethod(methodName).invoke(en).toString();
                if (invoke.equals(value.toString())) {
                    return en;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private static String getMethodIdentifier(Class<?> enumType) {
        Method[] methods = enumType.getDeclaredMethods();
        String name = null;
        for (Method method : methods) {
            int mod = method.getModifiers();
            if (Modifier.isPublic(mod) && !Modifier.isStatic(mod) && !Modifier.isFinal(mod)) {
                name = method.getName();
                break;
            }
        }
        return name;
    }

    // 测试
    public static void main(String[] args) {
        System.out.println(EnumUtils.valueIn("5", AnimalType.class));
        System.out.println(EnumUtils.valueIn("2", AnimalType.class));
        System.out.println(EnumUtils.valueIn("2", AnimalType.PERSON, AnimalType.DOG));
    }
}
