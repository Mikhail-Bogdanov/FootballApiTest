package com.example.data.utility

sealed class Event {
    data class PositiveFeedback(val text: String): Event()
    data class NegativeFeedback(val text: String): Event()
}