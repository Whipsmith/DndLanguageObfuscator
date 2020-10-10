package nz.co.opsmobile.ddlanguageobfuscator

import com.google.common.truth.Truth.assertThat
import nz.co.opsmobile.ddlanguageobfuscator.languages.words
import nz.co.opsmobile.ddlanguageobfuscator.utility.TestRandom
import org.junit.Before
import org.junit.Test

class LanguageObfuscatorTest {
lateinit var undertest: LanguageObfuscator

    private val input = "This is an input for the test presented to you"
    private val source = words

    @Before
    fun setUp() {
        undertest = LanguageObfuscator(input, RandomisedTranslator(source))
    }

    @Test
    fun `test that 100 percent returns the input exactly`() {
        val proficiency = 100

        val actual = undertest.obfuscate(proficiency)

        assertThat(actual).isEqualTo(input)
    }

    @Test
    fun `test that 0 proficiency obfuscates the string completely`() {
        val noSimpleWordsInput = "This input for test presented you"
        val proficiency = 0
        undertest = LanguageObfuscator(noSimpleWordsInput, RandomisedTranslator(source))
        val split = noSimpleWordsInput.split(" ")

        val actualSplit = undertest.obfuscate(proficiency).split(" ")

        println(actualSplit)
        split.forEachIndexed { index, actual ->
            assertThat(actual).doesNotMatch(actualSplit[index])
        }
    }

    @Test
    fun `test that obfuscated word is always obfuscated in the same way`() {
        val repeatedInput = "Apples Apples Oranges Oranges"
        undertest = LanguageObfuscator(repeatedInput, RandomisedTranslator(source))

        val proficiency = 0

        val actual = undertest.obfuscate(proficiency)

        println("Actual = $actual")
        val splitActually = actual.split(" ")

        assertThat(splitActually.count { it == splitActually[0] }).isEqualTo(2)
        assertThat(splitActually.count { it == splitActually[splitActually.lastIndex] }).isEqualTo(2)

    }

    @Test
    fun `test that capitalisation is ignored when comparing words`() {
        val repeatedInput = "Apples apples oranges Oranges"
        undertest = LanguageObfuscator(repeatedInput, RandomisedTranslator(source))

        val proficiency = 0

        val actual = undertest.obfuscate(proficiency)

        val splitActually = actual.split(" ")

        assertThat(splitActually.count { it == splitActually[0] }).isEqualTo(2)
        assertThat(splitActually.count { it == splitActually[splitActually.lastIndex] }).isEqualTo(2)
    }

    @Test
    fun `test that proficiency up to 50 percent has basic words removed`() {
        undertest = LanguageObfuscator(input, RandomisedTranslator(source), randomiser = TestRandom())
        val proficiency = 50

        val actual = undertest.obfuscate(proficiency)
        val expected = "This input for test presented you"

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test that words are only used for one translation`() {
        val longInput = "Another bother, question. " +
                "Travel porcupine! Tomato. Toward, pork. Hello " +
                "Friendly tooth knife. Greetings through sun, snow, and tax returns."
        
        undertest = LanguageObfuscator(longInput, RandomisedTranslator(source, randomiser = TestRandom()), randomiser = TestRandom())

        val actual = undertest.obfuscate(0)
        
        assertThat(actual.split(" ")).containsNoDuplicates()
    }

    @Test
    fun `test that excluded words are not translated`() {
        val excluded = listOf("This", "is")

        undertest = LanguageObfuscator(input, RandomisedTranslator(source), excluded)

        val actual = undertest.obfuscate(0)

        val split = actual.split(" ")
        assertThat(split[0]).isEqualTo("This")
        assertThat(split[1]).isEqualTo("is")
    }
}