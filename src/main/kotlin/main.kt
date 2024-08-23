import kotlin.math.max

fun main() {

    var monthlyAmount = 0
    var dailyAmount = 0

    while (true) {
        println("Введите сумму перевода (в руб.) :")
        val currentAmount = readln().toInt()
        val transferFee: Double = calculateTransferFee(cardType = "Mastercard", monthlyAmount, currentAmount)
        val totalAmount = currentAmount + transferFee.toInt()
        println("Сумма перевода (в руб.) : $currentAmount")
        dailyAmount += currentAmount
        monthlyAmount += currentAmount
        val result = checkLimits(dailyAmount, monthlyAmount)

        if (!result) {
            println("Лимит превышен!")
            break
        }
        println("Комиссия за перевод составит (в руб.): $transferFee")
        println("Итого сумма к оплате (в руб.) : $totalAmount")
    }
}

fun checkLimits(dailyAmount: Int, monthlyAmount: Int): Boolean {
    val dailyLimit = 150_000
    val monthlyLimit = 600_000

    if (dailyAmount > dailyLimit || monthlyAmount > monthlyLimit) {
        return false
    } else return true
}

fun calculateTransferFee(
    cardType: String = "Мир",
    monthlyAmount: Int,
    currentAmount: Int
): Double {
    val mastercardFreeLimit = 75000

    val transferFee = when (cardType) {
        "Visa" -> max(currentAmount * 0.0075, 35.0)
        "Mastercard" ->
            if (monthlyAmount < mastercardFreeLimit &&
                (currentAmount + monthlyAmount) > mastercardFreeLimit
            ) {
                (currentAmount - (mastercardFreeLimit - monthlyAmount)) * 0.006 + 20
            } else if (monthlyAmount > mastercardFreeLimit) {
                currentAmount * 0.006 + 20
            } else {
                0.0
            }

        else -> 0.0
    }
    return transferFee
}
