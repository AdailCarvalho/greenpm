package br.com.green.greenpm.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */
public class AuthException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3436623134565190172L;

    public AuthException(Class<?> clazz, String... searchParamsMap) {
        super(AuthException.generateMessage(clazz.getSimpleName(), 
                AuthException.toMap(String.class, String.class,  searchParamsMap)));
    }
    
    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) + 
                " was unable to authenticate the following user => " + searchParams;
    }
    
    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, String... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}