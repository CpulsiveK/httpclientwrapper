package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for extracting error objects from exception messages.
 */
public class ClientErrorPayloadExtractor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ClientErrorPayloadExtractor() {}

    /**
     * Extracts an error object from the exception message.
     *
     * @param exception the exception containing the error message
     * @param <T>       the type of the exception
     * @return the extracted error object as a JSON node, or null if no JSON object is found in the message
     * @throws JsonProcessingException if there is a problem processing the JSON
     */
    public static <T extends RuntimeException> Object getErrorObject(T exception) throws JsonProcessingException {
        String regex = "\\{.*?}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(exception.getMessage());

        if (!matcher.find()) return null;

        return objectMapper.readTree(matcher.group());
    }

    /**
     * Extracts an error object from the exception message and converts it to a specified type.
     *
     * @param exception   the exception containing the error message
     * @param returnValue the class of the return type
     * @param <T>         the type of the exception
     * @param <R>         the type of the return value
     * @return the extracted error object converted to the specified type, or null if no JSON object is found in the message
     * @throws JsonProcessingException if there is a problem processing the JSON
     */
    public static <T extends RuntimeException, R> R getErrorObject(T exception, Class<R> returnValue) throws JsonProcessingException {
        String regex = "\\{.*?}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(exception.getMessage());

        if (!matcher.find()) return null;

        JsonNode jsonNode = objectMapper.readTree(matcher.group());

        return objectMapper.convertValue(jsonNode, returnValue);
    }
}