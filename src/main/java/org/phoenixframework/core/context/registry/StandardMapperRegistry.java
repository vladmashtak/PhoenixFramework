package org.phoenixframework.core.context.registry;

import org.phoenixframework.core.exception.PhoenixException;
import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.mapper.ResultMapper;
import org.phoenixframework.core.mapper.standard.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 */

public class StandardMapperRegistry {

    private final Map<Class<?>, CustomResultMapper<?>> registry = new HashMap<>();

    public StandardMapperRegistry() {
        registry.put(Boolean.class, new BooleanResultMapper());
        registry.put(Byte.class, new ByteResultMapper());
        registry.put(Short.class, new ShortResultMapper());
        registry.put(Integer.class, new IntegerResultMapper());
        registry.put(Long.class, new LongResultMapper());
        registry.put(Float.class, new FloatResultMapper());
        registry.put(Double.class, new DoubleResultMapper());
        registry.put(BigDecimal.class, new BigDecimalResultMapper());
        registry.put(String.class, new StringResultMapper());
        registry.put(byte[].class, new ByteArrayResultMapper());
        registry.put(Date.class, new DateResultMapper());
        registry.put(Time.class, new TimeResultMapper());
        registry.put(Timestamp.class, new TimestampResultMapper());
    }

    @SuppressWarnings("unchecked")
    public <T> ResultMapper<T> getMapper(Class<T> classType) {
        ResultMapper<?> standardMapper = registry.get(classType);
        if (standardMapper == null) {
            throw new PhoenixException("Result mapper for standard type \"" + classType.getSimpleName() + "\" is not registered");
        }
        return (ResultMapper<T>) standardMapper;
    }
}
