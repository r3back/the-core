package com.qualityplus.anvil.base.config.impl

import com.qualityplus.anvil.api.config.ConfigFiles
import com.qualityplus.anvil.base.config.Commands
import com.qualityplus.anvil.base.config.Config
import com.qualityplus.anvil.base.config.Inventories
import com.qualityplus.anvil.base.config.Messages
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.platform.core.annotation.Component

@Component
class AnvilFiles : ConfigFiles<Config?, Inventories?, Messages?, Commands?> {
    @Inject
    private val inventories: Inventories? = null

    @Inject
    private val messages: Messages? = null

    @Inject
    private val commands: Commands? = null

    @Inject
    private val config: Config? = null
    override fun config(): Config? {
        return config
    }

    override fun inventories(): Inventories? {
        return inventories
    }

    override fun messages(): Messages? {
        return messages
    }

    override fun commands(): Commands? {
        return commands
    }

    override fun reloadFiles() {
        config!!.load()
        messages!!.load()
        inventories!!.load()
        commands!!.load()
    }
}