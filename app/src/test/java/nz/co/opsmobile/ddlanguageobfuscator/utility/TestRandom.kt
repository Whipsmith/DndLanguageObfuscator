package nz.co.opsmobile.ddlanguageobfuscator.utility

import kotlin.random.Random

class TestRandom(vararg nextNumber: Int = intArrayOf(1)) : Random() {
    private val nextNumberList = nextNumber.toMutableList()
    private var lastNumber: Int = 1
        get() {
            field = nextNumberList.removeFirstOrNull() ?: field
            return field
        }


    override fun nextBits(bitCount: Int): Int {
        return lastNumber
    }

    override fun nextInt(from: Int, until: Int): Int {
        val number = lastNumber
        return number.takeIf { it in from..until }
            ?: throw IndexOutOfBoundsException("Supplied number: $number is not in range of $from to $until")
    }
}