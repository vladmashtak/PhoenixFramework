package org.phoenixframework.core.context.registry;

import org.phoenixframework.core.mapper.ObjectTransformer;
import org.phoenixframework.core.mapper.ResultMapper;
import org.phoenixframework.core.mapper.single_wrap.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 */

public class DomainTransformerRegistry {

    private final Map<Class<?>, ObjectTransformer> registry = new HashMap<>();

    public DomainTransformerRegistry() {
        registerTransformer(Boolean.class, new BooleanResultMapper());
        registerTransformer(Byte.class, new ByteResultMapper());
        registerTransformer(Short.class, new ShortResultMapper());
        registerTransformer(Integer.class, new IntegerResultMapper());
        registerTransformer(Long.class, new LongResultMapper());
        registerTransformer(Float.class, new FloatResultMapper());
        registerTransformer(Double.class, new DoubleResultMapper());
        registerTransformer(BigDecimal.class, new BigDecimalResultMapper());
        registerTransformer(String.class, new StringResultMapper());
        registerTransformer(byte[].class, new ByteArrayResultMapper());
        registerTransformer(Date.class, new DateResultMapper());
        registerTransformer(Time.class, new TimeResultMapper());
        registerTransformer(Timestamp.class, new TimestampResultMapper());
    }

    public ObjectTransformer getTransformer(Class<?> classType) {
        return registry.get(classType);
    }

    @SuppressWarnings("unchecked")
    public <T> ResultMapper<T> getMapper(Class<T> classType) {
        return (ResultMapper<T>) getTransformer(classType);
    }

    public void registerTransformer(Class<?> classType, ObjectTransformer transformer) {
        registry.put(classType, transformer);
    }
}
