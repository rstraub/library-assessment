package nl.rstraub.library.assessment

data class Book(val title: String) {
    fun loanedBy(): Member? {
        return null
    }

    var isLoanedOut = false
}
