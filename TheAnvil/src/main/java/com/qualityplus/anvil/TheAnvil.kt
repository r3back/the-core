package com.qualityplus.anvil

import com.qualityplus.anvil.api.TheAnvilAPI
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.platform.core.annotation.Scan
import lombok.Getter

@Scan(deep = true)
class TheAnvil() : OkaeriSilentPlugin() {
    companion object Api {
        @Inject
        lateinit var api: TheAnvilAPI

        fun getApi(): TheAnvilAPI {
            return api;
        }
    }
}