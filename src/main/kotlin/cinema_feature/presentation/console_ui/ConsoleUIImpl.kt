package com.ilyanvk.cinema_feature.presentation.console_ui

import com.ilyanvk.cinema_feature.domain.cinema.Cinema
import com.ilyanvk.cinema_feature.domain.model.Session
import com.ilyanvk.cinema_feature.domain.user.UserService
import com.ilyanvk.cinema_feature.presentation.input_reader.InputReader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.system.exitProcess

class ConsoleUIImpl(
    private val cinema: Cinema,
    private val userService: UserService,
    private val inputReader: InputReader,
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
        println("1. Display movies")
        println("2. Display sessions for a movie")
        println("3. Sell ticket")
        println("4. Return ticket")
        println("5. Add movie")
        println("6. Add session")
        println("7. Delete movie")
        println("8. Delete session")
        println("9. Edit movie")
        println("10. Edit session")
        println("11. Change password")
        println("12. Delete account")
        println("13. Log out")
        println("14. Exit")
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
            3 -> sellTicket()
            4 -> returnTicket()
            5 -> addMovie()
            6 -> addSession()
            7 -> deleteMovie()
            8 -> deleteSession()
            9 -> editMovie()
            10 -> editSession()
            11 -> changePassword()
            12 -> deleteAccount()
            13 -> logOut()
            14 -> exit()
            else -> println("Invalid choice.")
        }
    }

    private fun displayMovies() {
        println("\n----- Movies -----")
        cinema.movies.sortedBy { it.id }.forEach { println("${it.id}. ${it.title} (${it.durationMin} min)") }
    }

    private fun displaySessions() {
        displayMovies()
        val movieId = inputReader.readInt("Enter the ID of the movie: ") ?: return
        val movie = cinema.movies.find { it.id == movieId }
        if (movie == null) {
            println("Movie not found.")
            return
        }
        println("\n----- Sessions for ${movie.title} -----")
        cinema.sessions.filter { it.movieId == movie.id }.sortedBy { it.time }.forEach {
            val timeString = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(it.time))
            println("Session ID ${it.id}:")
            println("\tTime: $timeString")
            println("\tAvailable seats: ${cinema.getAvailableSeats(it.id).sorted()}")
            println("\tBooked seats: ${cinema.getBookedSeats(it.id).sorted()}")
        }
    }

    private fun sellTicket() {
        val session = inputSession() ?: return
        val seat = inputReader.readInt("Enter the seat number to sell a ticket: ") ?: return
        try {
            val ticket = cinema.sellTicket(session.id, seat)
            println("Ticket sold successfully. Ticket ID: ${ticket.id}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun returnTicket() {
        val ticketId = inputReader.readInt("Enter the ticket ID to return: ") ?: return
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
        val sessionId = inputReader.readInt("Enter the ID of the session: ") ?: return null
        return cinema.sessions.find { it.id == sessionId } ?: run { println("Session not found."); null }
    }

    private fun addMovie() {
        val title = inputReader.readString("Input title of the movie: ")
        val duration = inputReader.readInt("Input duration of $title in minutes: ") ?: return
        cinema.addMovie(title, duration)
        println("$title successfully added")
    }

    private fun addSession() {
        displayMovies()
        val movieId = inputReader.readInt("Enter movie ID: ") ?: return
        val date = inputReader.readDate("Enter session time (yyyy-MM-dd HH:mm): ") ?: return
        cinema.addSession(movieId, date.time)
        println("Session added successfully.")
    }

    private fun deleteMovie() {
        displayMovies()
        val movieId = inputReader.readInt("Enter the ID of the movie: ") ?: return
        cinema.deleteMovie(movieId)
        println("Movie and its sessions deleted successfully.")
    }

    private fun deleteSession() {
        val session = inputSession() ?: return
        cinema.deleteSession(session.id)
        println("Session deleted successfully.")
    }

    private fun editMovie() {
        displayMovies()
        val movieId = inputReader.readInt("Enter the ID of the movie: ") ?: return
        val title = inputReader.readString("Input new title of the movie: ")
        val duration = inputReader.readInt("Input new duration of the movie in minutes: ") ?: return
        try {
            cinema.editMovie(movieId, title, duration)
            println("Movie edited successfully.")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun editSession() {
        val session = inputSession() ?: return
        val movieId = inputReader.readInt("Enter new movie ID: ") ?: return
        if (movieId !in cinema.movies.map { it.id }) {
            println("Movie not found.")
            return
        }
        val date = inputReader.readDate("Enter new session time (yyyy-MM-dd HH:mm): ") ?: return
        try {
            cinema.editSession(session.id, movieId, date.time)
            println("Session edited successfully.")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun logIn() {
        while (true) {
            println("Input 1 to log in and 2 to sign up.")
            val choice = inputReader.readInt() ?: continue
            val login = inputReader.readString("Login: ")
            val password = inputReader.readString("Password: ")
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
        val oldPassword = inputReader.readString("Input old password: ")
        val newPassword = inputReader.readString("Input new password: ")
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
}
