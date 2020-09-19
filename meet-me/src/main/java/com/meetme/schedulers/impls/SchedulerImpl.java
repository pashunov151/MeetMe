package com.meetme.schedulers.impls;

import com.meetme.schedulers.Scheduler;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerImpl implements Scheduler {

    @Override
    @Scheduled(cron = "0 0 */6 ? * *")
    @CacheEvict(value = "cachedUsers", allEntries = true)
    public void clearCache() {
        System.out.println("Cache for 'cachedUser' was cleared.");
    }
}
