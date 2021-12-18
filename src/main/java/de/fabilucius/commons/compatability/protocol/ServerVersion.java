package de.fabilucius.commons.compatability.protocol;

import org.bukkit.Bukkit;

import java.util.Arrays;

public enum ServerVersion {

    v1_8("1.8", 0),
    v1_9("1.9", 1),
    v1_10("1.10", 2),
    v1_11("1.11", 3),
    v1_12("1.12", 4),
    v1_13("1.13", 5),
    v1_14("1.14", 6),
    v1_15("1.15", 7),
    v1_16("1.16", 8),
    v1_17("1.17", 9),
    v1_18("1.18", 10),
    UNKNOWN("unknown", 999);

    ServerVersion(String serverVersion, int order) {
        this.serverVersion = serverVersion;
        this.order = order;
    }

    private static final ServerVersion CURRENT_SERVER_VERSION = fetchServerVersion();
    private final String serverVersion;
    private final int order;

    public static boolean isCurrentVersionHigherOrEqual(ServerVersion serverVersion) {
        return CURRENT_SERVER_VERSION.getOrder() >= serverVersion.getOrder();
    }

    public static boolean isCurrentVersionLowerOrEqual(ServerVersion serverVersion) {
        return CURRENT_SERVER_VERSION.getOrder() <= serverVersion.getOrder();
    }

    public static ServerVersion fetchServerVersion() {
        return Arrays.stream(values())
                .filter(version -> Bukkit.getVersion().contains(version.getServerVersion()))
                .findFirst()
                .orElse(UNKNOWN);
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public int getOrder() {
        return order;
    }
}