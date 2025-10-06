package com.example.lldkotlinwithbeginner.BookMyShow

// Defines various fixed categories used throughout the system
enum class MovieFormat { TWO_D, THREE_D, IMAX, FOUR_DX }
enum class Certificate { U, UA, A, PG13, R }
enum class SeatType { STANDARD, PREMIUM, RECLINER, VIP }
enum class ScreenType { STANDARD, THREE_D, IMAX, FOUR_DX }
enum class BookingStatus { PENDING_PAYMENT, CONFIRMED, CANCELLED }
enum class PaymentMethod { CARD, UPI, NET_BANKING, WALLET }
enum class PaymentStatus { INITIATED, SUCCESS, FAILED, REFUNDED }