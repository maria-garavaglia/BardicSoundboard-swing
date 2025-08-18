package com.dearmariarenie.bardicsoundboard.models;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCharacterModel
{
    @Test
    public void testSaveLoadCharacter() throws IOException
    {
        var filename = "target/test-classes/TestSaveCharacter.json";

        var testOutput = new File(filename);
        testOutput.createNewFile();
        var expected = new CharacterModel()
            .setName("TestChar")
            .addSpell(new SpellModel("Fireball", "Fireball.mp3"))
            .addSpell(new SpellModel("Vicious Mockery", "Vicious Mockery.mp3"))
            .addSpell(new SpellModel("Healing Word", "Healing Word.mp3"));
        expected.saveAs(testOutput);

        var actual = CharacterModel.load(testOutput);

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSpells(), actual.getSpells());
    }
}
