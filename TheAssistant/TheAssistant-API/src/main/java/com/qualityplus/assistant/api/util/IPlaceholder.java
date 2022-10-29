package com.qualityplus.assistant.api.util;

import java.util.List;

public interface IPlaceholder {
    String process(String line);

    List<String> processList(List<String> line);

    boolean isListPlaceholder();
}
