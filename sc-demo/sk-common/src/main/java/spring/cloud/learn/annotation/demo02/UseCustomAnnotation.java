package spring.cloud.learn.annotation.demo02;

import java.lang.reflect.AnnotatedElement;

public class UseCustomAnnotation {
    public static void main(String [] args) {
        Class<SetCustomAnnotation> classObject = SetCustomAnnotation.class;
        readAnnotation(classObject);
    }

    static void readAnnotation(AnnotatedElement element) {
        try {
            System.out.println("Annotation element values: \n");
            if (element.isAnnotationPresent(TypeHeader.class)) {
                // getAnnotation returns Annotation type
                TypeHeader header = element.getAnnotation(TypeHeader.class);

                System.out.println("Developer: " + header.developer());
                System.out.println("Last Modified: " + header.lastModified());

                // teamMembers returned as String []
                System.out.print("Team members: ");
                for (String member : header.teamMembers())
                    System.out.print(member + ", ");
                System.out.print("\n");

                System.out.println("Meaning of Life: "+ header.meaningOfLife());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}