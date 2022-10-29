package com.qualityplus.assistant.api;

import com.qualityplus.assistant.api.commands.CommandProvider;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import com.qualityplus.assistant.api.nms.NMS;
import com.qualityplus.assistant.api.service.AddonsService;
import org.bukkit.plugin.Plugin;

public interface TheAssistantAPI {
    CommandProvider<AssistantCommand> getCommandProvider();
    DependencyResolver getDependencyResolver();
    AddonsService getAddons();
    Plugin getPlugin();
    NMS getNms();
}
