package com.qualityplus.minions.util;

import com.qualityplus.assistant.util.random.RandomSelector;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
public final class VectorSection {
    private final Vector first;
    private final Vector second;
    private final Vector third;

    @NotNull
    public List<Vector> getFirsts(){
        return Collections.singletonList(first);
    }

    @NotNull
    public List<Vector> getSeconds(){
        return Arrays.asList(first, second);
    }

    @NotNull
    public List<Vector> getThirds(){
        return Arrays.asList(first, second, third);
    }


    /*@Nullable
    public Vector getRandom(){
        return RandomSelector.getRandom(getAll());
    }*/
}
