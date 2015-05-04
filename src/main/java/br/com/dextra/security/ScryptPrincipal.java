package br.com.dextra.security;

import java.security.Principal;

public interface ScryptPrincipal extends Principal {
    
    public String getPassword();
    public String[] getRoles();
    
} 