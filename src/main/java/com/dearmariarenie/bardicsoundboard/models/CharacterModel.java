package com.dearmariarenie.bardicsoundboard.models;

import com.dearmariarenie.bardicsoundboard.utils.Fmt;
import java.nio.file.Path;
import java.util.Map;

public class CharacterModel
{
    private String name;
    private final Map<String, Path> spells;

    public CharacterModel(String name, Map<String, Path> spells)
    {
        this.name = name;
        this.spells = spells;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addSpell(String spellName, Path audioFile)
    {
        if (spells.containsKey(spellName))
        {
            // TODO custom exceptions?
            throw new RuntimeException(
                Fmt.format("Spell '{}' already exists", spellName)
            );
        }

        // TODO check if audio file exists? Not sure if that should be here or
        //  elsewhere. Or maybe in multiple places for redundancy?

        spells.put(spellName, audioFile);
    }

    public void editSpell(
        String oldSpellName,
        String newSpellName,
        Path newAudioFile
    ){
        // Just remove the old spell entry and add a new one, it's easier than
        // futzing around with renaming existing entries
        removeSpell(oldSpellName);
        addSpell(newSpellName, newAudioFile);
    }

    public void removeSpell(String spellName)
    {
        spells.remove(spellName);
    }

    public Path getSpellAudio(String spellName)
    {
        if (!spells.containsKey(spellName))
        {
            throw new RuntimeException(
                Fmt.format("Spell '{}' not found", spellName)
            );
        }
        return spells.get(spellName);
    }
}
