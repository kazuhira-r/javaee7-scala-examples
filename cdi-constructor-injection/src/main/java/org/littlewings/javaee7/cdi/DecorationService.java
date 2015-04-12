package org.littlewings.javaee7.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

/*
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
*/

public interface DecorationService {
    String join(String prefix, String suffix, String... tokens);
}

@Typed(DecorationService.class)
@ApplicationScoped
class DecorationServiceImpl implements DecorationService {
    private StringJoinService joiner;

    @Inject
    public DecorationServiceImpl(StringJoinService joiner) {
        this.joiner = joiner;
    }

    public String join(String prefix, String suffix, String... tokens) {
        return prefix + joiner.join(", ", tokens) + suffix;
    }
}
