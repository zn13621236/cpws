/**
 *
 */
package com.casual.feed.jersey.exception;

/**
 * @author Aaron Yang
 */
public class CpwsServiceException extends RuntimeException {
    private final int status;
    private final String messageCode;
    private final Object[] parameters;

    public CpwsServiceException(Throwable cause, int status, String messageCode, Object... parameters) {
        super(messageCode, cause);
        this.messageCode = messageCode;
        this.parameters = parameters;
        this.status = status;
    }

    public CpwsServiceException(int status, String messageCode, Object... parameters) {
        this(null, status, messageCode, parameters);
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }


    /**
     * @return the parameters
     */
    public Object[] getParameters() {
        return parameters;
    }

    /**
     * @return the loggable
     */
    public boolean isLoggable() {
        if (getCause() != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getMessageCode() {
        return messageCode;
    }
}
