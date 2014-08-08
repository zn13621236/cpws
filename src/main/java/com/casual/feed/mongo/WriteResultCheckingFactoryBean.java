/**
 *
 */
package com.casual.feed.mongo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A factory bean for construction of a WriteResultChecking instance
 *
 * @author ayang
 */
@Component("writeResultChecking")
public class WriteResultCheckingFactoryBean implements FactoryBean<WriteResultChecking>, InitializingBean {
    /**
     * name of write result checking
     */
    @Value("${mongo.write.result.checking}")
    private String writeResultChecking;

    private WriteResultChecking resultChecking;

    public WriteResultChecking getObject() {
        return resultChecking;
    }

    public Class<?> getObjectType() {
        return WriteResultChecking.class;
    }

    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isBlank(writeResultChecking)) {
            resultChecking = WriteResultChecking.NONE;
        } else {
            resultChecking = WriteResultChecking.valueOf(writeResultChecking.trim().toUpperCase());
        }
    }
}