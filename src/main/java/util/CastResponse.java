package util;

import java.util.LinkedHashMap;

public interface CastResponse<R> {
  R castResponse(LinkedHashMap linkedHashMap, Class<R> value);
}
