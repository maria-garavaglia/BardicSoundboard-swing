package com.dearmariarenie.bardicsoundboard.models;

import com.dearmariarenie.bardicsoundboard.utils.Fmt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CharacterModel
{
    @JsonProperty("charName")
    private String name = "";
    @JsonProperty
    private Map<String, String> spells = new HashMap<>();

    public CharacterModel()
    {
    }

    public CharacterModel(String name)
    {
        this.name = name;
    }

    public CharacterModel(String name, Map<String, String> spells)
    {
        this.name = name;
        this.spells = spells;
    }

    public String getName()
    {
        return name;
    }

    public CharacterModel setName(String name)
    {
        this.name = name;
        return this;
    }

    public Map<String, String> getSpells()
    {
        return spells;
    }

    public CharacterModel addSpell(String spellName, String audioFilename)
    {
        if (spells.containsKey(spellName))
        {
            // TODO custom exceptions?
            throw new RuntimeException(
                Fmt.format("Spell '{}' already exists", spellName)
            );
        }

        spells.put(spellName, audioFilename);
        return this;
    }

    public CharacterModel editSpell(
        String oldSpellName,
        String newSpellName,
        String newAudioFilename
    ){
        // Just remove the old spell entry and add a new one, it's easier than
        // futzing around with renaming existing entries
        removeSpell(oldSpellName);
        addSpell(newSpellName, newAudioFilename);
        return this;
    }

    public CharacterModel removeSpell(String spellName)
    {
        spells.remove(spellName);
        return this;
    }

    public String getSpellAudio(String spellName)
    {
        if (!spells.containsKey(spellName))
        {
            throw new RuntimeException(
                Fmt.format("Spell '{}' not found", spellName)
            );
        }
        return spells.get(spellName);
    }

    public void save(String filename) throws IOException
    {
        // TODO new file creation if needed?
        new ObjectMapper().writeValue(new File(filename), this);
    }

    public static CharacterModel load(String filename) throws IOException
    {
        return new ObjectMapper().readValue(
            new File(filename),
            CharacterModel.class
        );
    }
}
