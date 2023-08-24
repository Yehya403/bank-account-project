fun main() {
    println("Welcome to your banking system. \n" +
            "What type of account would you like to create?\n" +
            "1. Debit account\n" +
            "2. Credit account\n"+
            "3. Checking account")
    var accountType : String = ""
    var userChoice : Int = 0
    while(accountType == ""){
        println("Choose an option (1, 2 or 3)")
        userChoice = readLine()?.toIntOrNull() ?: 0
        println("The selected option is ${userChoice}")
        when(userChoice){
            1-> accountType="debit"
            2-> accountType="credit"
            3-> accountType="checking"
            else -> {
                println("option you choosed isn't right , try again")
                continue
            }
        }
    }
    println("You have created a ${accountType} account.")

    println("Enter ur account 's balance")
    var accountBalance = readLine()?.toIntOrNull() ?: 0
    println("account balance is ${accountBalance}")

    println("Enter the amount to be deposited/withdrawn")
    val money = readLine()?.toIntOrNull() ?: 0
    println("amount to be deposited/withdrawn is ${money}")

    //var output = 0

    fun withdraw(amount : Int) : Int{
    accountBalance -= amount
    println("You successfully withdrew ${amount} dollars. The current balance is ${accountBalance} dollars.")
        return amount
    }
    //output = withdraw(money)

    fun debitWithdraw(amount: Int) : Int{
        if (accountBalance == 0){
            println("Can't withdraw, no money on this account!")
            return accountBalance
        } else if (amount > accountBalance){
            println("Not enough money on this account! The current balance is ${accountBalance} dollars.")
            return 0
        } else {
            return withdraw(amount)
        }
    }
    //output = debitWithdraw(money)

    fun deposit(amount: Int): Int{
        accountBalance += amount
        println("the amount of money deposited to your bank account is ${amount}and " +
                "the account’s updated balance is ${accountBalance}")
        return amount
    }
    //output=deposit(money)

    fun creditDeposit(amount: Int): Int {
        if(accountBalance == 0){
            println("This account is completely paid off! Do not deposit money!")
            return accountBalance
        } else if (accountBalance + amount > 0) {
            println("Deposit failed, you tried to pay off an amount greater than the credit balance. " +
                    "The current balance is ${accountBalance} dollars.")
            return 0
        } else if (amount == -accountBalance){
            accountBalance = 0
            println("You have paid off this account!")
            return amount
        } else{
            return deposit(amount)
        }
    }
    //output=creditDeposit(money)
    //println("The amount you deposited is ${output} dollars.")

    fun transfer(mode: String) {
        val transferAmount : Int
        when (mode){
            "withdraw" -> {
                if(accountType == "debit"){
                    transferAmount = debitWithdraw(money)
                } else {
                    transferAmount = withdraw(money)
                }
                println("The amount you withdrew is ${transferAmount} dollars.")

            }
            "deposit" -> {
                if(accountType == "credit"){
                    transferAmount = creditDeposit(money)
                } else {
                    transferAmount =deposit(money)
                }
                println("The amount you deposited is ${transferAmount} dollars.")
            }
            else -> return
        }
    }

    var isSystemOpen = true
    var option = 0

    while(isSystemOpen == true){
        println("What would you like to do?")
        println("1. Check bank account balance")
        println("2. Withdraw money")
        println("3. Deposit money")
        println("4. Close the system")
        println("Which option do you choose? (1, 2, 3 or 4)")

        val input = readLine()
        option = input?.toIntOrNull() ?: 0
        println("The selected option is ${option}.")

        when (option) {
            1 -> println("The current balance is ${accountBalance} dollars.")
            2 -> transfer("withdraw")
            3 -> transfer("deposit")
            4 -> {
                isSystemOpen = false
                println("The system is closed")
            }
            else -> continue
        }
    }

}