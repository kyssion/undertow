package com.magic.sso.util.simpleSql;
@Deprecated
public class SimpleSqlParams implements SqlParams {

    private String[] params;

    public SimpleSqlParams(String... p) {
        this.params = p;
    }

    public SimpleSqlParams() {
        super();
    }

    @Override
    public String[] getParams() {
        return this.params;
    }

    public static SimpleSqlParams create(String... s) {
        return new SimpleSqlParams(s);
    }
}
