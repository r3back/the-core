package com.qualityplus.anvil.api.config

import com.qualityplus.assistant.api.config.ConfigReloader

interface ConfigFiles<C, I, M, CMD> : ConfigReloader {
    fun config(): C
    fun inventories(): I
    fun messages(): M
    fun commands(): CMD
}