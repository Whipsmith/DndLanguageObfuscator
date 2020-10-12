package nz.co.opsmobile.ddlanguageobfuscator

import nz.co.opsmobile.ddlanguageobfuscator.dice.Die
import nz.co.opsmobile.ddlanguageobfuscator.dice.RandomisedRoll
import nz.co.opsmobile.ddlanguageobfuscator.dice.Roll


class LanguageObfuscator(
    private val input: String,
    private val translator: RandomisedTranslator,
    private val exclusions: List<String> = listOf(),
    private val roll: Roll = RandomisedRoll(Die.D100)
) {
    private val simpleWords = listOf(
        "a",
        "an",
        "is",
        "to",
        "am",
        "on",
        "by",
        "the"
    )

    fun obfuscate(proficiency: Int): String {
        return input.split(delimiters = arrayOf(" "))
            .filter { it in exclusions || (proficiency > 50 || it !in simpleWords) }
            .joinToString(separator = " ") {
                if (it in exclusions || passedProficiencyCheck(proficiency)) it else obfuscateWord(
                    it
                )
            }
    }

    private fun passedProficiencyCheck(proficiency: Int) =
        roll.result().total <= proficiency

    private fun obfuscateWord(word: String): String {
        return translator.translateWord(word)
    }
}

