package com.p2pInternetloan.base.config;

/**
 * @Description: session监听
 * @Author: cpc
 * @Date: 2019-10-17 15:51
 * @Version: V1.0
 */
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

public class BDSessionListener implements SessionListener {

    private final AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
    }

    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();

    }

    public int getSessionCount() {
        return sessionCount.get();
    }




}
