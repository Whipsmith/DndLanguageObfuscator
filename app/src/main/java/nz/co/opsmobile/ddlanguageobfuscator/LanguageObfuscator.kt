package nz.co.opsmobile.ddlanguageobfuscator

import kotlin.random.Random
import kotlin.random.nextInt


class LanguageObfuscator(
    private val input: String,
    private val translator: RandomisedTranslator,
    private val exclusions: List<String> = listOf(),
    private val randomiser: Random = Random
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
        randomiser.nextInt(1..100) <= proficiency

    private fun obfuscateWord(word: String): String {
        return translator.translateWord(word)
    }
}

