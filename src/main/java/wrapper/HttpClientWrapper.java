package wrapper;

import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface HttpClientWrapper<T, R> extends HttpClient {
    @GetExchange("{path}")
    R getRequest(@PathVariable String path);
    @GetExchange("{path}")
    R getRequest(@PathVariable String path, @RequestBody T body);
    @GetExchange("{path}")
    R getRequest(@PathVariable String path, @RequestParam Map<String, String> params);
    @GetExchange("{path}")
    R getRequest(@PathVariable String path, @RequestHeader Map<String, String> headers, @RequestBody T body);
}