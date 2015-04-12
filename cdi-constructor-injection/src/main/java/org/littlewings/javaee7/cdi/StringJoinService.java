package org.littlewings.javaee7.cdi;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StringJoinService {
    public String join(String delimiter, String... tokens) {
        return String.join(delimiter, tokens);
    }
}
