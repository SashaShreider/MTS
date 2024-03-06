package ru.mts.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import ru.mts.entity.enums.AnimalType;
import ru.mts.servise.CreateAnimalServiceImpl;

import java.lang.reflect.Field;

@Configuration
public class CreateAnimalServiceImplPostPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalServiceImpl) {
            try {
                Field field = CreateAnimalServiceImpl.class.getDeclaredField("type");
                field.setAccessible(true);
                field.set(bean, AnimalType.randomAnimalType());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return bean;
    }
}

