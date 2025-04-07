package org.alham.alhamfirst.common.enums

enum class Intensity(
    val minRange :Double,
    val maxRange :Double
) {
    LOW(0.1, 0.3),
    MEDIUM(0.4, 0.6),
    HIGH(0.7, 0.9)
}