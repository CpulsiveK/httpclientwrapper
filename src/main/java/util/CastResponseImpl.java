package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedHashMap;

public class CastResponseImpl<R> implements CastResponse<R> {
  private final ObjectMapper objectMapper;

  public CastResponseImpl(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public R castResponse(LinkedHashMap linkedHashMap, Class<R> value) {
    return objectMapper.convertValue(linkedHashMap, value);
  }
}
