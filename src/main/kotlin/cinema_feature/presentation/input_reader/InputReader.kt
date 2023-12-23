package com.ilyanvk.cinema_feature.presentation.input_reader

import java.util.Date

/**
 * This interface defines the methods for reading different types of user input.
 */
interface InputReader {
    /**
     * Reads an integer value from the user.
     *
     * @param prompt The prompt to display to the user before reading the input.
     * @return The integer value read from the user, or null if the input was not a valid integer.
     */
    fun readInt(prompt: String = ""): Int?

    /**
     * Reads a string value from the user.
     *
     * @param prompt The prompt to display to the user before reading the input.
     * @return The string value read from the user.
     */
    fun readString(prompt: String = ""): String

    /**
     * Reads a date value from the user.
     *
     * @param prompt The prompt to display to the user before reading the input.
     * @return The date value read from the user, or null if the input was not a valid date.
     */
    fun readDate(prompt: String = ""): Date?
}
