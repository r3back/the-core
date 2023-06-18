package com.qualityplus.anvil.base.gui

import com.qualityplus.anvil.api.box.Box
import com.qualityplus.assistant.api.util.IPlaceholder
import com.qualityplus.assistant.inventory.GUI
import com.qualityplus.assistant.inventory.Item
import com.qualityplus.assistant.inventory.SimpleGUI
import java.util.*

abstract class AnvilGUI : GUI {
    protected val box: Box?
    protected var uuid: UUID? = null

    constructor(size: Int, title: String?, box: Box?) : super(size, title) {
        this.box = box
    }

    constructor(simpleGUI: SimpleGUI?, box: Box?) : super(simpleGUI) {
        this.box = box
    }

    override fun setItem(item: Item) {
        setItem(item, box!!.getFiles()!!.config()!!.loreWrapper)
    }

    override fun setItem(item: Item, placeholderList: List<IPlaceholder>) {
        setItem(item, placeholderList, box!!.getFiles()!!.config()!!.loreWrapper)
    }
}