package nz.co.opsmobile.ddlanguageobfuscator.dice

open class Die(val range: IntRange) {
    object D2: Die(1..2)
    object D4: Die(1..4)
    object D6: Die(1..6)
    object D8: Die(1..8)
    object D10: Die(1..10)
    object D12: Die(1..12)
    object D20: Die(1..20)
    object D100: Die(1..100)
    class Custom(customRange: IntRange): Die(customRange)

    override fun toString(): String {
        return "D${range.last}"
    }
}