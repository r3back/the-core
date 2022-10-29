package com.qualityplus.pets.util;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.actionbar.ActionBarUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.pets.api.pet.Pets;
import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.base.pet.tracker.PetEntityTracker;
import com.qualityplus.pets.persistance.data.UserData;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@UtilityClass
public class PetPlaceholderUtil {
    public List<IPlaceholder> getPlaceholders(PetEntity petEntity){
        Optional<Pet> pet = Optional.ofNullable(Pets.getByID(petEntity.getEgg().getPet().getId()));
        Optional<PetEntity> entity = PetEntityTracker.getByID(petEntity.getPetUniqueId());

        double maxXp = entity.map(PetEntity::getMaxXp).orElse(1D);
        double xp = entity.map(PetEntity::getXp).orElse(1D);
        double percentage = ActionBarUtils.getPercentageFromTotal(xp, maxXp);


        return PlaceholderBuilder.create(
                new Placeholder("pet_egg_egg_displayname", pet.map(p -> p.getPetEgg().getEggDisplayName()).orElse("")),
                new Placeholder("pet_egg_displayname", pet.map(p -> p.getPetEgg().getDisplayName()).orElse("")),

                new Placeholder("pet_egg_description", pet.map(p -> p.getPetEgg().getLore()).orElse(Collections.emptyList())),
                new Placeholder("pet_level_number", entity.map(PetEntity::getLevel).orElse(1)),
                new Placeholder("pet_level_progress", percentage),
                new Placeholder("pet_action_bar", ActionBarUtils.getReplacedBar(percentage)),
                new Placeholder("pet_xp", xp),
                new Placeholder("pet_max_xp", maxXp)
        ).get();
    }

    public PlaceholderBuilder getPetPlaceholders(Pet pet){
        return PlaceholderBuilder.create(
                new Placeholder("pet_egg_egg_displayname", pet.getPetEgg().getEggDisplayName()),
                new Placeholder("pet_egg_displayname", pet.getPetEgg().getDisplayName()),
                new Placeholder("pet_egg_description", pet.getPetEgg().getLore())
        );
    }

    public PlaceholderBuilder getPetPlaceholders(UUID petUuid){
        Optional<PetEntity> entity = PetEntityTracker.getByID(petUuid);

        double maxXp = entity.map(PetEntity::getMaxXp).orElse(1D);
        double xp = entity.map(PetEntity::getXp).orElse(1D);
        double percentage = ActionBarUtils.getPercentageFromTotal(xp, maxXp);


        return PlaceholderBuilder.create(
                new Placeholder("pet_level_number", entity.map(PetEntity::getLevel).orElse(1)),
                new Placeholder("pet_level_progress", percentage),
                new Placeholder("pet_action_bar", ActionBarUtils.getReplacedBar(percentage)),
                new Placeholder("pet_xp", xp),
                new Placeholder("pet_max_xp", maxXp)
        );
    }
}
