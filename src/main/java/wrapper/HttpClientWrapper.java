package wrapper;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * The HttpClientWrapper interface extends the HttpClient interface and provides additional methods
 * for HTTP GET requests.
 *
 * <p>This interface is generic and can be used with any type that represents the body of a request.
 *
 * @param <T> the type of the request body
 * @author Clifford Kwakye
 * @version 1.0
 * @since 1.0
 */
public interface HttpClientWrapper<T> extends HttpClient {

  /**
   * Sends a GET request to the specified path.
   *
   * @param path the path of the request
   * @return the response as a LinkedHashMap
   */
  @GetExchange("{path}")
  LinkedHashMap getRequest(@PathVariable String path);

  /**
   * Sends a GET request to the specified path with a request body.
   *
   * @param path the path of the request
   * @param body the body of the request
   * @return the response as a LinkedHashMap
   */
  @GetExchange("{path}")
  LinkedHashMap getRequest(@PathVariable String path, @RequestBody T body);

  /**
   * Sends a GET request to the specified path with request parameters.
   *
   * @param path the path of the request
   * @param params the request parameters
   * @return the response as a LinkedHashMap
   */
  @GetExchange("{path}")
  LinkedHashMap getRequest(@PathVariable String path, @RequestParam Map<String, String> params);

  /**
   * Sends a GET request to the specified path with request headers and a request body.
   *
   * @param path the path of the request
   * @param headers the request headers
   * @param body the body of the request
   * @return the response as a LinkedHashMap
   */
  @GetExchange("{path}")
  LinkedHashMap getRequest(
      @PathVariable String path, @RequestHeader Map<String, String> headers, @RequestBody T body);
}
