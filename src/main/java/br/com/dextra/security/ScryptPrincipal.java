package br.com.dextra.security;

import java.util.List;

import org.apache.catalina.realm.GenericPrincipal;

public class ScryptPrincipal extends GenericPrincipal {

    public ScryptPrincipal(String name, String password, List<String> roles) {
        super(name, password, roles);
    }

}
