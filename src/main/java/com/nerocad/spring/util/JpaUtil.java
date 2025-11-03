package com.nerocad.spring.util;

import com.nerocad.spring.Main;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class JpaUtil {
    private static final Logger logger = (Logger) LogManager.getLogger(JpaUtil.class);
    private static EntityManagerFactory emf = null;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("avitron-bot");
        } catch (Exception e) {
            logger.info("Ошибка инициализации EntityManagerFactory");
        }
    }

    private JpaUtil() {}

    public static EntityManager getEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory не инициализирован");
        }
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
