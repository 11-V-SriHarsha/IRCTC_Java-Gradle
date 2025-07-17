package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    @JsonProperty("ticket_id")
    private String ticketId;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("source")
    private String source;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("date_of_travel")
    private String dateOfTravel;
    @JsonProperty("train")
    private Train train;

    public Ticket() {}

    public Ticket(String ticketId,String userId,String source,String destination,String dateOfTravel,Train train) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public Train getTrain() {
        return train;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @JsonIgnore // Ignore this getter during JSON serialization/deserialization
    public String getTicketInfo() {
        return String.format("Ticket ID: %s\n" +
                             "  User ID: %s\n" +
                             "  Source: %s\n" +
                             "  Destination: %s\n" +
                             "  Date: %s\n" +
                             "  Train: %s",
                ticketId, userId, source, destination, dateOfTravel, (train != null ? train.getTrainInfo() : "N/A"));
    }
}
