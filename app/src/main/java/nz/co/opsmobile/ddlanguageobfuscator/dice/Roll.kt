package nz.co.opsmobile.ddlanguageobfuscator.dice

import kotlin.random.Random
import kotlin.random.nextInt

class Roll(
    val die: Die,
    val number: Int = 1,
    val modifier: Int = 0,
    private val randomiser: Random = Random
) {
    fun result(): RollResult {

        val rolls = (1..number).map {
            val rolled = randomiser.nextInt(die.range)
            DieResult(die, rolled)
        }

        return RollResult(
            rolls,
            rolls.sumBy { it.rolled } + modifier
        )
    }
}