package com.yueking.core.shiro;

import org.apache.shiro.realm.jdbc.JdbcRealm;

public class JdbcSaltRealm extends JdbcRealm {
    public JdbcSaltRealm() {
        setSaltStyle(SaltStyle.COLUMN);
    }
}
