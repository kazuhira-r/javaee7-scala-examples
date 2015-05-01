package org.littlewings.javaee7.interceptor;

import java.io.Serializable;
import javax.enterprise.context.Dependent;

@Dependent
public class AccessModifierMessageService implements Serializable {
    private static final long serialVersionUID = 1L;

    @AddStar
    public String join(String separator, String... tokens) {
        return String.join(separator, tokens);
    }

    @AddStar
    String joinPackagePrivate(String separator, String... tokens) {
        return String.join(separator, tokens);
    }
}
