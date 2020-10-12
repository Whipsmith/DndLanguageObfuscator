package nz.co.opsmobile.ddlanguageobfuscator.dice

import kotlin.random.Random
import kotlin.random.nextInt

class RandomisedRoll(
    val die: Die,
    val number: Int = 1,
    val modifier: Int = 0,
    private val randomiser: Random = Random
) : Roll {
    override fun result(): RollResult {

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