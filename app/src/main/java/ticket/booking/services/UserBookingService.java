package ticket.booking.services;

import ticket.booking.entities.User;
import ticket.booking.entities.Train;
import ticket.booking.util.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.ArrayList;


public class UserBookingService {

    private User user;

    private static final String USERS_PATH = "users.json"; // Path is relative to project root

    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUsers();
    }

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public List<User> loadUsers() throws IOException {
        File usersFile = new File(USERS_PATH);
        if (!usersFile.exists()) {
            // Create an empty users.json if it doesn't exist
            objectMapper.writeValue(usersFile, new ArrayList<>());
        }
        userList = objectMapper.readValue(usersFile, new TypeReference<List<User>>() {});
        return userList;
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        if (foundUser.isPresent()) {
            this.user = foundUser.get(); // Set the user for this service instance
            return true;
        }
        return false;
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            this.user = user1; // Set the user as logged in after signup
            return Boolean.TRUE;
        }
        catch(IOException e) {
            return Boolean.FALSE;
        }
    }

    public User getLoggedInUser() {
        return this.user;
    }

    public void saveUserListToFile() throws IOException{
        // When saving, write to a file in the project root or a designated data directory
        // This is where the users.json will be updated during runtime
        objectMapper.writeValue(new File(USERS_PATH), userList);
    }

    public void fetchBooking() {
        user.printTickets();
    }

    public void bookSeat(String userId, String trainNo, String source, String destination) {
        // Find the user
        Optional<User> foundUser = userList.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
        if (foundUser.isPresent()) {
            User currentUser = foundUser.get();
            TrainService trainService = new TrainService();
            Train foundTrain = trainService.getTrainByNo(trainNo);
            if (foundTrain != null) {
                // Logic to book a seat (this would involve updating the Train's available seats and adding to user's tickets)
                // For now, let's just add a dummy ticket to the user's bookings
                String bookingId = UUID.randomUUID().toString();
                currentUser.addTicket(bookingId, trainNo, source, destination);
                try {
                    saveUserListToFile();
                } catch (IOException e) {
                    System.err.println("Error saving user list after booking: " + e.getMessage());
                }
                // Refined success message
                System.out.println("\n--- Booking Confirmation ---");
                System.out.println("User: " + currentUser.getName());
                System.out.println("Train Number: " + trainNo);
                System.out.println("Source: " + source);
                System.out.println("Destination: " + destination);
                System.out.println("Booking ID: " + bookingId);
                System.out.println("----------------------------\n");
            } else {
                System.out.println("Train with number " + trainNo + " not found. Booking failed.");
            }
        } else {
            System.out.println("User with ID " + userId + " not found. Booking failed.");
        }
    }

    private static final int CONSOLE_WIDTH = 80; // Assuming same console width as App.java

    private static void printCentered(String text) {
        int padding = (CONSOLE_WIDTH - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

    public Boolean cancelBooking(String userId, String ticketId) {
        Optional<User> foundUser = userList.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
        if (foundUser.isPresent()) {
            User currentUser = foundUser.get();
            Boolean cancelled = currentUser.cancelTicket(ticketId);
            if (cancelled) {
                try {
                    saveUserListToFile();
                    printCentered("Booking with ID " + ticketId + " cancelled successfully.\n");
                } catch (IOException e) {
                    System.err.println("Error saving user list after cancellation: " + e.getMessage());
                }
            } else {
                printCentered("Booking with ID " + ticketId + " not found or could not be cancelled.\n");
            }
            return cancelled;
        }
        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source, String destination) {
        TrainService trainService = new TrainService();
        return trainService.searchTrains(source, destination);
    }
}
