package com.qualityplus.assistant.base.dependency;

import com.qualityplus.assistant.TheAssistantPlugin;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsualDependencies {
    private final String PROTOCOL_LIB_PLUGIN_NAME = "ProtocolLib";
    private final Boolean IS_PROTOCOL_LIB;

    static {
        IS_PROTOCOL_LIB = TheAssistantPlugin.getAPI()
                .getDependencyResolver()
                .isPlugin(PROTOCOL_LIB_PLUGIN_NAME);
    }

    public Boolean isProtocolLib(){
        return IS_PROTOCOL_LIB;
    }
}
