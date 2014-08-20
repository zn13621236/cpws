/**
 *
 */
package com.casual.feed.resource.domain.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;

/**
 * @author Aaron Yang
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ErrorResponse extends HashMap<String, Object> {
    public ErrorResponse(String code, String message) {
        put("type", "error");
        put("code", code);
        put("message", message);
    }
}
