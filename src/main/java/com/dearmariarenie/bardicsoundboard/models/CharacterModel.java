package com.dearmariarenie.bardicsoundboard.models;

import com.dearmariarenie.bardicsoundboard.utils.Fmt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterModel
{
    private static final Logger logger = LoggerFactory.getLogger(CharacterModel.class);

    @JsonProperty("charName")
    private String name = "";
    @JsonProperty
    private List<SpellModel> spells = new ArrayList<>();

    public CharacterModel()
    {
    }

    public CharacterModel(String name)
    {
        this.name = name;
    }

    public CharacterModel(String name, List<SpellModel> spells)
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

    public List<SpellModel> getSpells()
    {
        return spells;
    }

    public CharacterModel addSpell(SpellModel newSpell)
    {
        // check if spell with the same name already exists
        if (spells.stream()
            .map(SpellModel::getName)
            .anyMatch(s -> s.equals(newSpell.getName()))
        ){
            // TODO custom exceptions?
            throw new RuntimeException(
                Fmt.format("Spell '{}' already exists", newSpell.getName())
            );
        }

        spells.add(newSpell);
        return this;
    }

    public CharacterModel editSpell(
        String oldSpellName,
        String newSpellName,
        String newAudioFilename
    ){
        findSpell(oldSpellName).orElseThrow()
            .setName(newSpellName)
            .setFile(newAudioFilename)
        ;

        return this;
    }

    public CharacterModel removeSpell(SpellModel toRemove)
    {
        spells.remove(toRemove);
        return this;
    }

    public Optional<SpellModel> findSpell(String spellName)
    {
        return spells.stream()
            .filter(s -> s.getName().equals(spellName))
            .findFirst()
        ;
    }

    public void save(String filename) throws IOException
    {
        // TODO new file creation if needed?
        new ObjectMapper().writeValue(new File(filename), this);
    }

    public static CharacterModel load(File file) throws IOException
    {
        var newChar = new ObjectMapper().readValue(file, CharacterModel.class);
        logger.info(
            "Loaded character {} with {} spells",
            newChar.name,
            newChar.spells.size()
        );
        return newChar;
    }
}
