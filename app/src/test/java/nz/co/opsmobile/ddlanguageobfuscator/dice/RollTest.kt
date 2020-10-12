package nz.co.opsmobile.ddlanguageobfuscator.dice

import com.google.common.truth.Truth.assertThat
import nz.co.opsmobile.ddlanguageobfuscator.dice.Die.*
import nz.co.opsmobile.ddlanguageobfuscator.utility.TestRandom
import org.junit.Test

class RollTest{

    @Test
    fun `test that roll result contains 1 die`() {
        val result = Roll(D6).result()
        assertThat(result.rolls).hasSize(1)
    }

    @Test
    fun `test that roll result contains rolled value`() {
        val expected = 3
        val result = Roll(D6, randomiser = TestRandom(expected)).result()
        assertThat(result.rolls[0].rolled).isEqualTo(expected)
    }

    @Test
    fun `test that value of one die is returned`() {
        val expected = 3
        val result = Roll(D6, randomiser = TestRandom(expected, 5)).result()
        assertThat(result.rolls[0].rolled).isEqualTo(expected)
    }

    @Test
    fun `test that the total of one roll is the grand total`() {
        val expected = 3
        val result = Roll(D6, randomiser = TestRandom(expected, 5)).result()
        assertThat(result.total).isEqualTo(expected)
    }

    @Test
    fun `test multiple dice`() {
        val expected = 4
        val result = Roll(D12, expected).result()
        assertThat(result.rolls.size).isEqualTo(expected)
    }

    @Test
    fun `test total of two rolls sum to total`() {
        val roll1 = 4
        val roll2 = 3
        val expected = roll1 + roll2
        val actual = Roll(D6, 2, randomiser = TestRandom(roll1, roll2)).result()
        assertThat(actual.total).isEqualTo(expected)
    }

    @Test
    fun `test that modifiers are applied`() {
        val roll1 = 4
        val roll2 = 3
        val modifier = 7
        val expected = roll1 + roll2 + modifier
        val actual = Roll(D6, 2, modifier, randomiser = TestRandom(roll1, roll2)).result()
        assertThat(actual.total).isEqualTo(expected)
    }

}