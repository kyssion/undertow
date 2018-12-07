package com.magic.sso.util.simpleSql;

public interface SqlParams {
    String[] getParams();

    default SqlParams simpleSqlParams(String... item) {
        return new SimpleSqlParams(item);
    }
}
