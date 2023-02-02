package com.euvatrates.euvrservice.services;

import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class CachingService {
    @CacheEvict(value = "evrJsonCache", allEntries = true)
    public void evictAllCacheValues() {}
}
