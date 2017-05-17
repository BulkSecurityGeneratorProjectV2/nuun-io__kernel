package io.nuun.kernel.core.internal.topology;

import static java.util.Arrays.stream;
import io.nuun.kernel.core.KernelException;
import io.nuun.kernel.spi.topology.InstanceBinding;
import io.nuun.kernel.spi.topology.LinkedBinding;
import io.nuun.kernel.spi.topology.TopologyDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Optional;

import javax.inject.Qualifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.BindingAnnotation;

public class TopologyDefinitionCore implements TopologyDefinition
{

    private Logger logger = LoggerFactory.getLogger(TopologyDefinitionCore.class);

    @Override
    public Optional<LinkedBinding> linkedBinding(Member candidate)
    {

        if (Method.class.equals(candidate.getClass()) && candidate.getName().startsWith("injects"))
        {
            Method m = (Method) candidate;

            if (m.getParameterCount() == 1)
            {

                Class<?> key = m.getParameterTypes()[0];
                Class<?> provided = m.getReturnType();

                Optional<Annotation> qualifier = qualifier(m.getParameterAnnotations()[0]);
                if (!qualifier.isPresent())
                {
                    return Optional.of(new LinkedBinding(key, provided));
                }
                else
                {
                    return Optional.of(new LinkedBinding(key, qualifier.get(), provided));
                }

            }

        }

        return Optional.empty();
    }

    @Override
    public Optional<InstanceBinding> instanceBinding(Member candidate)
    {
        if (Field.class.equals(candidate.getClass()))
        {

            Object instance = getValue((Field) candidate);

            if (instance == null)
            {
                throw new KernelException(
                        "Topology %s field %s is null, Please set a value.", candidate.getDeclaringClass().getSimpleName(), candidate.getName());
            }

            Field f = (Field) candidate;
            Class<?> key = f.getType();
            Optional<Annotation> qualifier = qualifier(f);

            if (qualifier.isPresent())
            {
                return Optional.of(new InstanceBinding(f.getType(), qualifier.get().annotationType(), instance));
            }
            else
            {
                return Optional.of(new InstanceBinding(f.getType(), instance));
            }
        }

        return Optional.empty();
    }

    @SuppressWarnings({
            "unchecked", "rawtypes"
    })
    private Optional<Annotation> qualifier(AccessibleObject m)
    {

        return qualifier(m.getAnnotations());
        // return stream(m.getAnnotations()).filter(
        // a -> a.annotationType().isAnnotationPresent(Qualifier.class) ||
        // a.annotationType().isAnnotationPresent(BindingAnnotation.class)).findFirst();

    }

    private Optional<Annotation> qualifier(Annotation[] annotations)
    {
        return stream(annotations).filter(
                a -> a.annotationType().isAnnotationPresent(Qualifier.class) || a.annotationType().isAnnotationPresent(BindingAnnotation.class)).findFirst();
    }

    private Object getValue(Field f)
    {
        try
        {
            // System.out.println( MyAppTopology.class.getField(f.getName()).get(null) ) ;

            return f.get(null);

        }
        catch (Exception e)
        {
            return null;
        }
    }

}
