package com.qualityplus.auction.persistence.data;

import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public final class AuctionHouse extends Document {
    private String name;
    private List<AuctionItem> normalItems = new ArrayList<>();
}
