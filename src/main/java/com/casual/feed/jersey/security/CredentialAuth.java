package com.casual.feed.jersey.security;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author: ayang
 */
@NameBinding
@Retention ( RetentionPolicy.RUNTIME )
public @interface CredentialAuth {
}
