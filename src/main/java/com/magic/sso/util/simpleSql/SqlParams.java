package com.magic.sso.util.simpleSql;

@Deprecated
public interface SqlParams {
    String[] getParams();

    default SqlParams simpleSqlParams(String... item) {
        return new SimpleSqlParams(item);
    }
}
