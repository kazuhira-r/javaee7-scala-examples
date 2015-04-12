package org.littlewings.javaee7.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

// スコープアノテーションを変更
// @ApplicationScoped
@Dependent
public class DecorationService {
    private StringJoinService joiner;

    @Inject
    public DecorationService(StringJoinService joiner) {
        this.joiner = joiner;
    }

    // デフォルトコンストラクタは削除
    // public DecorationService() { }

    public String join(String prefix, String suffix, String... tokens) {
        return prefix + joiner.join(", ", tokens) + suffix;
    }
}
