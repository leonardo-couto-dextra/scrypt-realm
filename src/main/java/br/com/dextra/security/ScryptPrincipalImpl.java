package br.com.dextra.security;

import java.util.List;

import org.apache.catalina.realm.GenericPrincipal;

class ScryptPrincipalImpl extends GenericPrincipal implements ScryptPrincipal {

    public ScryptPrincipalImpl(String name, String password, List<String> roles) {
        super(name, password, roles);
    }
    
    @Override
    public void setPassword(String password) {
        super.password = password;
    }

}
