package spring.cloud.learn.annotation.demo02;

// This is the annotation being applied to a class
@TypeHeader(developer = "Bob Bee",
        lastModified = "2013-02-12",
        teamMembers = { "Ann", "Dan", "Fran" },
        meaningOfLife = 42)
public class SetCustomAnnotation {
    // Class contents go here
}