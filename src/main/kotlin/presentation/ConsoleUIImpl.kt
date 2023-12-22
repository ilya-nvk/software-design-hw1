package com.ilyanvk.presentation

import com.ilyanvk.domain.cinema.Cinema
import com.ilyanvk.domain.model.Session
import com.ilyanvk.domain.user.UserService
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.system.exitProcess

class ConsoleUIImpl(
    private val cinema: Cinema,
    private val userService: UserService,
) : ConsoleUI {
    override fun start() {
        while (true) {
            if (userService.currentUser == null) logIn()
            printMenu()
            handleChoice(readChoice())
        }
    }

    private fun printMenu() {
        println("\n----- Cinema Management System -----")
        println("1. Display Movies")
        println("2. Display Sessions for a Movie")
        println("3. Buy Ticket")
        println("4. Return Ticket")
        println("5. Add movie")
        println("6. Add session")
        println("7. Delete movie")
        println("8. Delete session")
        println("9. Change password")
        println("10. Delete account")
        println("11. Log out")
        println("12. Exit")
        print("Enter your choice: ")
    }

    private fun readChoice(): Int {
        return try {
            readln().toInt()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            -1
        }
    }

    private fun handleChoice(choice: Int) {
        when (choice) {
            1 -> displayMovies()
            2 -> displaySessions()
            3 -> buyTicket()
            4 -> returnTicket()
            5 -> addMovie()
            6 -> addSession()
            7 -> deleteMovie()
            8 -> deleteSession()
            9 -> changePassword()
            10 -> deleteAccount()
            11 -> logOut()
            12 -> exit()
            else -> println("Invalid choice.")
        }
    }

    private fun displayMovies() {
        println("\n----- Movies -----")
        cinema.movies.forEach { println("${it.id}. ${it.title} (${it.durationMin} min)") }
    }

    private fun displaySessions() {
        displayMovies()
        val movieId = readInt("Enter the ID of the movie: ") ?: return
        val movie = cinema.movies.find { it.id == movieId }
        if (movie == null) {
            println("Movie not found.")
            return
        }
        println("\n----- Sessions for ${movie.title} -----")
        cinema.sessions.filter { it.movieId == movie.id }.forEach {
            val timeString = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(it.time))
            println("Session ${it.id}:")
            println("\tTime: $timeString")
            println("\tAvailable seats: ${cinema.getAvailableSeats(it.id)}")
            println("\tBooked seats: ${cinema.getBookedSeats(it.id)}")
        }
    }

    private fun buyTicket() {
        val session = inputSession() ?: return
        val seat = readInt("Enter the seat number to sell a ticket: ") ?: return
        try {
            val ticket = cinema.sellTicket(session.id, seat)
            println("Ticket sold successfully. Ticket ID: ${ticket.id}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun returnTicket() {
        val ticketId = readInt("Enter the ticket ID to return: ") ?: return
        try {
            cinema.returnTicket(ticketId)
            println("Ticket returned successfully.")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun exit() {
        println("Exiting the Cinema Management System. Goodbye!")
        exitProcess(0)
    }

    private fun inputSession(): Session? {
        displaySessions()
        val sessionId = readInt("Enter the ID of the session: ") ?: return null
        return cinema.sessions.find { it.id == sessionId } ?: run { println("Session not found."); null }
    }

    private fun addMovie() {
        val title = readString("Input title of the movie: ")
        val duration = readInt("Input duration of $title in minutes: ") ?: return
        cinema.addMovie(title, duration)
        println("$title successfully added")
    }

    private fun addSession() {
        displayMovies()
        val movieId = readInt("Enter movie ID: ") ?: return
        val date = readDate("Enter session time (yyyy-MM-dd HH:mm): ") ?: return
        cinema.addSession(movieId, date.time)
        println("Session added successfully.")
    }

    private fun deleteMovie() {
        displayMovies()
        val movieId = readInt("Enter the ID of the movie: ") ?: return
        cinema.deleteMovie(movieId)
        println("Movie and its sessions deleted successfully.")
    }

    private fun deleteSession() {
        val session = inputSession() ?: return
        cinema.deleteSession(session.id)
        println("Session deleted successfully.")
    }

    private fun logIn() {
        while (true) {
            println("Input 1 to log in and 2 to sign up.")
            val choice = readInt() ?: continue
            val login = readString("Login: ")
            val password = readString("Password: ")
            try {
                if (choice == 1) userService.logIn(login, password)
                else userService.register(login, password)
                return
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    private fun changePassword() {
        val oldPassword = readString("Input old password: ")
        val newPassword = readString("Input new password: ")
        try {
            userService.changePassword(oldPassword, newPassword)
            println("Password changed successfully.")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun deleteAccount() {
        try {
            userService.deleteAccount()
            println("Your account was successfully deleted.")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun logOut() {
        try {
            userService.logOut()
            println("Logged out successfully.")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun readInt(prompt: String = ""): Int? {
        print(prompt)
        return try {
            readln().toInt()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    private fun readString(prompt: String = ""): String {
        print(prompt)
        return readln()
    }

    private fun readDate(prompt: String = ""): Date? {
        print(prompt)
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            dateFormat.parse(readln())
        } catch (e: Exception) {
            println("Error parsing date: ${e.message}")
            null
        }
    }
}