package com.euvatrates.euvrservice.configurations;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheEventLogger
        implements CacheEventListener<Object, Object> {
    private static final Logger logger = LoggerFactory.getLogger(CacheEventLogger.class);
    @Override
    public void onEvent(
            CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        logger.info(String.format("Cache event triggered: %s",cacheEvent.getType()));
    }
}