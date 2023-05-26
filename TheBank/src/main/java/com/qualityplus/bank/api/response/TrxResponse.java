package com.qualityplus.bank.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class TrxResponse {
    private double amount;
    private double playerBalance;
    private double bankBalance;
}
