package com.qualityplus.minions.base.gui.main.setup;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.MinionEntity;

import java.util.List;

public interface PlaceholdersSetup {
    List<IPlaceholder> getPlaceholders(Box box, MinionEntity minionEntity);
}
