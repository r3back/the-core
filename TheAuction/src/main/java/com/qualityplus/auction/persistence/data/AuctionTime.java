package com.qualityplus.auction.persistence.data;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.time.Timer;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public final class AuctionTime extends OkaeriConfig {
    private Timer timer;
    private double fee;
    private XMaterial icon;
    private int slot;
}
