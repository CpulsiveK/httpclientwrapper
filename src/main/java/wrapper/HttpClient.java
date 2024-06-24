package wrapper;

import org.springframework.web.service.annotation.HttpExchange;

/**
 * The HttpClient interface provides a contract for HTTP communication.
 *
 * <p>This interface is annotated with @HttpExchange, indicating that it is part of the HTTP
 * communication workflow. Implementations of this interface should provide concrete logic for HTTP
 * requests and responses.
 *
 * @author Clifford Kwakye
 * @version 1.0
 * @since 1.0
 */
@HttpExchange
public interface HttpClient {}
