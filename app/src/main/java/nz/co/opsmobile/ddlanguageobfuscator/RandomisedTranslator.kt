package nz.co.opsmobile.ddlanguageobfuscator

import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class RandomisedTranslator(
    source: List<String>,
    private val randomiser: Random = Random
) : Translator {
    private val map = mutableMapOf<String, String>()
    private val sourceWords = source.toMutableList()

    override fun translateWord(word: String): String {
        return map.computeIfAbsent(word.toLowerCase(Locale.ROOT)) {
            val index = randomiser.nextInt(0..sourceWords.lastIndex)
            sourceWords.removeAt(index)
        }
    }
}