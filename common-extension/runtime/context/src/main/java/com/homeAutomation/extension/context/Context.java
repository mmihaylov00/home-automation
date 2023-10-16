package com.homeAutomation.extension.context;

import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class Context {
    private static final ThreadLocal<Context> THREADLOCAL = new InheritableThreadLocal<>();
    private String deviceName;
    private String browserInformation;
    private boolean isLocalAreaNetwork = false;
    private String ip;
    private UUID userId;
    private ContextUser user;

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
