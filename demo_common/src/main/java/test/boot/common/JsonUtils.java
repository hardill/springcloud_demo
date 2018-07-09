package test.boot.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * json 工具类
 * 
 * @author Administrator
 *
 */
@Slf4j
public class JsonUtils {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	public static String encode(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			log.error("encode(Object)"); //$NON-NLS-1$
		} catch (JsonMappingException e) {
			log.error("encode(Object)"); //$NON-NLS-1$
		} catch (IOException e) {
			log.error("encode(Object)"); //$NON-NLS-1$
		}
		return null;
	}

	/**
	 * 将json string反序列化成对象
	 *
	 * @param json
	 * @param valueType
	 * @return
	 */
	public static <T> T decode(String json, Class<T> valueType) {
		try {
			return objectMapper.readValue(json, valueType);
		} catch (JsonParseException e) {
			log.error("decode(String, Class<T>)");
		} catch (JsonMappingException e) {
			log.error("decode(String, Class<T>)");
		} catch (IOException e) {
			log.error("decode(String, Class<T>)");
		}
		return null;
	}

	/**
	 * 将json array反序列化为对象
	 *
	 * @param json
	 * @param jsonTypeReference
	 * @return
	 */
	public static <T> T decode(String json, TypeReference<T> jsonTypeReference) {
		try {
			return (T) objectMapper.readValue(json, jsonTypeReference);
		} catch (JsonParseException e) {
			log.error("decode(String, JsonTypeReference<T>)");
		} catch (JsonMappingException e) {
			log.error("decode(String, JsonTypeReference<T>)");
		} catch (IOException e) {
			log.error("decode(String, JsonTypeReference<T>)");
		}
		return null;
	}
}