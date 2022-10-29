package com.qualityplus.assistant.api.commands.details;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Exclude;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.command.CommandSender;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class CommandDetails extends OkaeriConfig {
    public List<String> aliases;
    public String description;
    public String permission;
    public String syntax;
    public boolean onlyForPlayers;
    public long cooldownInSeconds;
    @Exclude
    public CooldownProvider<CommandSender> cooldownProvider;
    public boolean enabled;
    public String labelProvider;

    public CommandDetails(List<String> aliases, String description, String syntax, String permission, boolean onlyForPlayers, long cooldownInSeconds,
                          boolean enabled, String labelProvider) {
        this.aliases = aliases;
        this.description = description;
        this.syntax = syntax;
        this.permission = permission;
        this.onlyForPlayers = onlyForPlayers;
        this.cooldownInSeconds = cooldownInSeconds;
        this.enabled = enabled;
        this.labelProvider = labelProvider;
    }

    public CooldownProvider<CommandSender> getCooldownProvider() {
        return cooldownProvider == null ? CooldownProvider.newInstance(Duration.ofSeconds(cooldownInSeconds)) : cooldownProvider;
    }

    public static class CooldownProvider<T>{
        protected final Map<T, Duration> cooldownTimes = new HashMap<>();
        protected final Duration duration;

        private CooldownProvider(Duration duration) {
            this.duration = duration;
        }

        public boolean isOnCooldown(T t) {
            return cooldownTimes.containsKey(t) && cooldownTimes.get(t).toMillis() > System.currentTimeMillis();
        }

        public Duration getRemainingTime(T t) {
            if (!isOnCooldown(t)) return Duration.ZERO;

            return cooldownTimes.get(t).minusMillis(System.currentTimeMillis());
        }

        public void applyCooldown(T t) {
            cooldownTimes.put(t, duration.plusMillis(System.currentTimeMillis()));
        }


        public static <T> CooldownProvider<T> newInstance(Duration duration) {
            return new CooldownProvider<>(duration);
        }

        public static <T> CooldownProvider<T> newPersistentInstance(String name, Duration duration) {
            throw new NotImplementedException();
        }
    }

}