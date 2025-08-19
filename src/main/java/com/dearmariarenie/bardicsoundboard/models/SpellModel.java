package com.dearmariarenie.bardicsoundboard.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Stores information about a character's spell
 */
public class SpellModel
{
    @JsonProperty
    private String name = "";
    @JsonProperty
    private String file = "";

    // default ctor for Jackson
    public SpellModel()
    {
    }

    public SpellModel(String name, String file)
    {
        this.name = name;
        this.file = file;
    }

    public String getName()
    {
        return name;
    }

    public SpellModel setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getFile()
    {
        return file;
    }

    public SpellModel setFile(String file)
    {
        this.file = file;
        return this;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        SpellModel that = (SpellModel)o;
        return Objects.equals(name, that.name);
        // Don't really need to compare file names
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, file);
    }
}
