package br.com.dextra.security;

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

    @Override
    protected boolean compareCredentials(String userCredentials, String serverCredentials) {
        return SCryptUtil.check(userCredentials, serverCredentials);
    }
    
}
