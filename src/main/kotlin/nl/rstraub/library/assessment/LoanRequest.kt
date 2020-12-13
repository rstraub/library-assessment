package nl.rstraub.library.assessment

data class LoanRequest(val book: String, val member: Member)

infix fun String.to(member: Member) = LoanRequest(this, member)
