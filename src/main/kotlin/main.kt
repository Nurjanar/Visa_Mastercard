import kotlin.math.max

fun main() {
    val dailyLimit = 150_000
    val monthlyLimit = 600_000
    var monthlyAmount = 0

    while (true) {
        println("Введите сумму перевода (в руб.) :")
        val currentAmount = readln().toInt()
        val transferFee: Double = calculateTransferFee(cardType = "Мир", monthlyAmount = 0, currentAmount)
        val totalAmount = currentAmount + transferFee.toInt()
        println("Сумма перевода (в руб.) : $currentAmount")
        monthlyAmount = +currentAmount
        if (currentAmount > dailyLimit || monthlyAmount > monthlyLimit) {
            println("Превышен лимит!")
            break
        }
        println("Комиссия за перевод составит (в руб.): $transferFee")
        println("Итого сумма к оплате (в руб.) : $totalAmount")
    }
}

fun calculateTransferFee(
    cardType: String = "Мир",
    monthlyAmount: Int = 0,
    currentAmount: Int
): Double {
    val transferFee = when (cardType) {
        "Visa" -> max(currentAmount * 0.0075, 35.0)
        "Mastercard" -> (currentAmount - (75000 - monthlyAmount)) * 0.006 + 20
        else -> 0.0
    }
    return transferFee
}
