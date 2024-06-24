package util;

import java.util.LinkedHashMap;

/**
 * The CastResponse interface provides a contract for casting a LinkedHashMap to a specified type.
 * <p>
 * This interface is generic and can be used with any type that you want to cast the LinkedHashMap to.
 * </p>
 *
 * @param <R> the type to cast the LinkedHashMap to
 * @author Clifford Kwakye
 * @version 1.0
 * @since 1.0
 */
public interface CastResponse<R> {

  /**
   * Casts a LinkedHashMap to the specified type.
   *
   * @param linkedHashMap the LinkedHashMap to cast
   * @param value the Class object of the type to cast to
   * @return the LinkedHashMap cast to the specified type
   */
  R castResponse(LinkedHashMap linkedHashMap, Class<R> value);
}
