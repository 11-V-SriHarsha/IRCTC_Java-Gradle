package ticket.booking.entities;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Train {
    @JsonProperty("train_id")
    private String trainId;
    @JsonProperty("train_no")
    private String trainNo;
    @JsonProperty("seats")
    private List<List<Integer>> seats;
    @JsonProperty("station_times")
    private Map<String, String> stationTimes;
    @JsonProperty("stations")
    private List<String> stations;

    public Train(){}

    public Train(String trainId,String trainNo,List<List<Integer>> seats,Map<String,String> stationTimes,List<String> stations) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }

    public List<String> getStations() {
        return stations;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public Map<String,String> getStationTimes() {
        return stationTimes;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setStationTimes(Map<String,String> stationTimes) {
        this.stationTimes = stationTimes;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    @JsonIgnore // Ignore this getter during JSON serialization/deserialization
    public String getTrainInfo() {
        return String.format("Train ID: %s and Train No: %s",trainId,trainNo);
    }
}
