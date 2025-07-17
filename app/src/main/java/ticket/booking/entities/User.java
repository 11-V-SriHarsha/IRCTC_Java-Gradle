package ticket.booking.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ticket.booking.services.TrainService;

public class User {

    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("hashed_password")
    private String hashPassword;
    @JsonProperty("tickets_booked")
    private List<Ticket> ticketBooked;
    @JsonIgnore
    private String password; // This field is for transient use (e.g., hashing during signup/login), not for persistence.

    public User(String name, String password, String hashPassword, List<Ticket> ticketBooked, String userId) {
        this.name = name;
        this.password = password; // Store plaintext temporarily for hashing
        this.hashPassword = hashPassword;
        this.ticketBooked = (ticketBooked != null) ? ticketBooked : new ArrayList<>();
        this.userId = userId;
    }

    public User(){}

    public void addTicket(String ticketId, String trainNo, String source, String destination) {
        // Fetch the train details using TrainService
        TrainService trainService = new TrainService();
        Train train = trainService.getTrainByNo(trainNo);

        if (train != null) {
            // For now, setting dateOfTravel as a dummy value. This should ideally come from user input.
            String dateOfTravel = "2024-12-31"; 
            Ticket newTicket = new Ticket(ticketId, this.userId, source, destination, dateOfTravel, train);
            if (this.ticketBooked == null) {
                this.ticketBooked = new ArrayList<>();
            }
            this.ticketBooked.add(newTicket);
        } else {
            System.out.println("Train with number " + trainNo + " not found. Ticket not added.");
        }
    }

    public String getName() {
        return name;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public List<Ticket> getTicketBooked() {
        return ticketBooked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTicketBooked(List<Ticket> ticketBooked) {
        this.ticketBooked = ticketBooked;
    }

    private static final int CONSOLE_WIDTH = 80; // Assuming same console width as App.java

    private static void printCentered(String text) {
        int padding = (CONSOLE_WIDTH - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

    public void printTickets() {
        if (ticketBooked == null || ticketBooked.isEmpty()) {
            printCentered("No tickets booked.\n");
        } else {
            for (Ticket ticket : ticketBooked) {
                String[] lines = ticket.getTicketInfo().split("\\n");
                for (String line : lines) {
                    printCentered(line);
                }
                System.out.println(); // Add a blank line between tickets
            }
        }
    }

    public boolean cancelTicket(String ticketId) {
        if (ticketBooked == null) 
            return false;
        boolean removed = ticketBooked.removeIf(ticket -> ticket.getTicketId().equals(ticketId));
        return removed;
    }

    // public String getTicketInfo() {
    //     StringBuilder sb = new StringBuilder();
    //     sb.append("Tickets for user: ").append(name).append("\n");
    //     if (ticketBooked == null || ticketBooked.isEmpty()) {
    //         sb.append("No tickets booked.\n");
    //     } else {
    //         for (Ticket ticket : ticketBooked) {
    //             sb.append("Ticket ID: ").append(ticket.getTicketId())
    //               .append(", Source: ").append(ticket.getSource())
    //               .append(", Destination: ").append(ticket.getDestination())
    //               .append(", Date: ").append(ticket.getDateOfTravel());
    //             if (ticket.getTrain() != null) {
    //                 sb.append(", Train: ").append(ticket.getTrain().toString());
    //             }
    //             sb.append("\n");
    //         }
    //     }
    //     return sb.toString();
    // }
}
