package util.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import util.CastResponse;

import java.util.LinkedHashMap;

/**
 * The CastResponseImpl class provides an implementation of the CastResponse interface.
 * <p>
 * This class uses Jackson's ObjectMapper to convert a LinkedHashMap to a specified type.
 * </p>
 *
 * @param <R> the type to cast the LinkedHashMap to
 * @author Clifford Kwakye
 * @version 1.0
 * @since 1.0
 */
public class CastResponseImpl<R> implements CastResponse<R> {
  private final ObjectMapper objectMapper;

  /**
   * Constructs a new CastResponseImpl with the specified ObjectMapper.
   *
   * @param objectMapper the ObjectMapper to use for casting
   */
  public CastResponseImpl(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * Casts a LinkedHashMap to the specified type using the ObjectMapper.
   *
   * @param linkedHashMap the LinkedHashMap to cast
   * @param value the Class object of the type to cast to
   * @return the LinkedHashMap cast to the specified type
   */
  @Override
  public R castResponse(LinkedHashMap linkedHashMap, Class<R> value) {
    return objectMapper.convertValue(linkedHashMap, value);
  }
}
