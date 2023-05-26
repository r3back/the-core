package com.qualityplus.bank.api.handler;

import com.qualityplus.bank.api.request.TrxRequest;
import com.qualityplus.bank.api.response.TrxResponse;

public interface TrxHandler {
    /**
     *
     * @param request {@link TrxRequest}
     * @return {@link TrxResponse}
     */
    public TrxResponse handle(final TrxRequest request);
}
