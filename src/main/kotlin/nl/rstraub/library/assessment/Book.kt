package nl.rstraub.library.assessment

data class Book(val title: String, val isbn: String) {
    var isLoanedOut = false
}
