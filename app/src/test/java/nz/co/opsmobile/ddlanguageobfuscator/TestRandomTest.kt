package nz.co.opsmobile.ddlanguageobfuscator

import com.google.common.truth.Truth.assertThat
import nz.co.opsmobile.ddlanguageobfuscator.utility.TestRandom
import org.junit.Test
import kotlin.random.nextInt


class TestRandomTest{

    @Test
    fun `test default value of 1`() {
        val testRandom = TestRandom()
        assertThat(testRandom.nextInt(0..100)).isEqualTo(1)
        assertThat(testRandom.nextInt(0..100)).isEqualTo(1)
        assertThat(testRandom.nextInt(0..100)).isEqualTo(1)
        assertThat(testRandom.nextInt(0..100)).isEqualTo(1)
    }

    @Test
    fun `test custom value is returned`() {
        val testRandom = TestRandom(3)
        assertThat(testRandom.nextInt(0..23)).isEqualTo(3)
    }

    @Test
    fun `test that multiple values are returned in order`() {
        val testRandom = TestRandom(2,3,4,5,6)
        assertThat(testRandom.nextInt(0..100)).isEqualTo(2)
        assertThat(testRandom.nextInt(0..34)).isEqualTo(3)
        assertThat(testRandom.nextInt(0..100)).isEqualTo(4)
        assertThat(testRandom.nextInt(0..55)).isEqualTo(5)
        assertThat(testRandom.nextInt(0..77)).isEqualTo(6)
    }

    @Test
    fun `test that last value is returned if no more are supplied`() {
        val testRandom = TestRandom(2,3,4)
        assertThat(testRandom.nextInt(0..100)).isEqualTo(2)
        assertThat(testRandom.nextInt(0..34)).isEqualTo(3)
        assertThat(testRandom.nextInt(0..100)).isEqualTo(4)
        assertThat(testRandom.nextInt(0..55)).isEqualTo(4)
        assertThat(testRandom.nextInt(0..77)).isEqualTo(4)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `test that exception is thrown if value is outside of range`() {
        val testRandom = TestRandom(20)
        testRandom.nextInt(0..4)
    }
}