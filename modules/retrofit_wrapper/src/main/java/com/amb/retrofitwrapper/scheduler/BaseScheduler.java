package com.amb.retrofitwrapper.scheduler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseScheduler {
    public interface SchedulerProvider {
        Scheduler mainThread();

        Scheduler io();
    }

    private static SchedulerProvider instance = new DefaultSchedulerProvider();

    public static void setInstance(SchedulerProvider instanceProvider) {
        instance = instanceProvider;
    }

    public static Scheduler mainThread() {
        return instance.mainThread();
    }

    public static Scheduler io() {
        return instance.io();
    }

    public static class DefaultSchedulerProvider implements SchedulerProvider {
        @Override
        public Scheduler mainThread() {
            return AndroidSchedulers.mainThread();
        }

        @Override
        public Scheduler io() {
            return Schedulers.io();
        }
    }
}
