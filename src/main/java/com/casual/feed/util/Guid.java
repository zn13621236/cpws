/**
 *
 */
package com.casual.feed.util;


import com.eaio.uuid.UUIDGen;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * A value object to represent a guid in the domain layer.<br>
 * A guid is a globally unique identifier. See the wiki for a detailed explanation:<br>
 * http://en.wikipedia.org/wiki/Globally_unique_identifier
 *
 * @author Aaron Yang
 */
public class Guid {
    /**
     * The guid state as a UUID object, which is just a wrapper on a pair of long values. The UUID class is wrapped, as
     * it does no validation on the actual value, and it's API contains many methods that are now useless, and are not
     * needed in the RN domain.
     */
    private final UUID guid;

    /**
     * String version of the guid. This is just a cached copy of the UUID in string format in it's standard canonical
     * form. This portion of the object state exists only for optimization where the string version is obtained many
     * times throughout the lifecycle of the object.
     */
    private String guidString;

    /**
     * Constructs a guid from a JDK UUID representation.
     *
     * @param guid A guid.
     */
    public Guid(UUID guid) {
        Assert.notNull(guid, "Input guid value is null");
        this.guid = guid;
    }

    /**
     * Return a guid based on a type 4 UUID random number.
     *
     * @return A new random guid.
     */
    public static Guid createRandomGuid() {
        return new Guid(new UUID(UUIDGen.newTime(), UUIDGen.getClockSeqAndNode()));
    }

    @Override
    public String toString() {
        // If the cached guid is not set, create it.
        if (guidString == null) {
            guidString = guid.toString();
        }
        // Return the canonical string form.
        return guidString;
    }

    /**
     * Returns the RN guid form. This is just a non-hyphenated version of the guid in upper case.<br>
     * The standard version in {@link #toString()} should be preferred.
     *
     * @return The RN guid format.
     */
    public String toGuidString() {
        return toString().replace("-", "");
    }

    @Override
    public int hashCode() {
        return guid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Guid)) {
            return false;
        }
        return (((Guid) obj).guid).equals(guid);
    }
}