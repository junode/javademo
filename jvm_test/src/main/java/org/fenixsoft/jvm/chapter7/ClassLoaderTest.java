package org.fenixsoft.jvm.chapter7;

/**
 * @author zzm
 */

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与instanceof关键字演示
 *
 * @author zzm
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        // 自定义类加载器加载的 ClassLoaderTest
        Object obj = myLoader.loadClass("org.fenixsoft.jvm.chapter7.ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());
        // org.fenixsoft.jvm.chapter7.ClassLoaderTest 由应用程序类加载器所加载的
        // 由不同类加载器加载的同一个类文件，在Java虚拟机中仍是两个独立的类
        System.out.println(obj instanceof org.fenixsoft.jvm.chapter7.ClassLoaderTest);
    }
}
