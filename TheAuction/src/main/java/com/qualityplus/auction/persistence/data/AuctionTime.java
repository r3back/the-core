package com.qualityplus.auction.persistence.data;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.time.HumanTime;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public final class AuctionTime extends OkaeriConfig {
    private HumanTime timer;
    private double fee;
    private XMaterial icon;
    private int slot;
}
