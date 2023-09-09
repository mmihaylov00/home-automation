package com.homeAutomation.extension.context;

import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class Context {
    private String macAddress;
    private String ip;

    private static final ThreadLocal<Context> THREADLOCAL = new InheritableThreadLocal<>();

    public static Context get() {
        Context context = THREADLOCAL.get();
        if (Objects.isNull(context)) {
            context = new Context();
            set(context);
        }
        return context;
    }

    public static void set(Context context) {
        THREADLOCAL.set(context);
    }

    public static void clear() {
        THREADLOCAL.remove();
    }
}
