package br.com.dextra.security;

import java.security.Principal;
import java.sql.Connection;
import java.util.ArrayList;

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.JDBCRealm;

import com.lambdaworks.crypto.SCryptUtil;

public class SCryptRealm extends JDBCRealm {
	
	protected static final String NAME = "SCryptRealm";
    
    @Override
    protected String getName() {
        return NAME;
    }
    
    @Override 
    public void setDigest(String digest) {
    	throw new UnsupportedOperationException("Setting the digest algorithm is not allowed");
    }

    protected boolean compareCredentials(String userCredentials, String serverCredentials) {
        return (serverCredentials == null) ? false : SCryptUtil.check(userCredentials, serverCredentials);
    }
    
    @Override
    public synchronized Principal authenticate(Connection dbConnection, String username, String credentials) {
        if (username == null || credentials == null) return null;

        String dbCredentials = getPassword(username);
        boolean validated = compareCredentials(credentials, dbCredentials);
        
        if (!validated) return null;
        
        ArrayList<String> roles = getRoles(username);
       	return new GenericPrincipal(username, credentials, roles);
    }

    
}
