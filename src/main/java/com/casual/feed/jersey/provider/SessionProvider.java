package com.casual.feed.jersey.provider;

import com.casual.feed.mongo.domain.Session;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceHandle;

/**
 * @author: ayang
 */
public class SessionProvider implements InjectionResolver<Session> {
	@Override
	public Object resolve(Injectee injectee, ServiceHandle<?> serviceHandle) {
		return null;
	}

	@Override
	public boolean isConstructorParameterIndicator() {
		return false;
	}

	@Override
	public boolean isMethodParameterIndicator() {
		return false;
	}
}
