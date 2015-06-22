package org.littlewings.javaee7.beanvalidation;

import javax.validation.constraints.Pattern;

public class Person {
    @MySize.List({
            @MySize(min = 3, max = 3),
            @MySize(min = 4, max = 6, groups = MyGroup.class)
    })
    public String firstName;

    @Pattern.List({
            @Pattern(regexp = "^磯.*"),
            @Pattern(regexp = ".*野$")
    })
    public String lastName;
}
