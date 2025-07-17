package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import java.util.stream.Collectors;
import java.io.File;
import java.io.InputStream;
import ticket.booking.entities.Train;

public class TrainService {

    private List<Train> trainList;
    private static final String TRAINS_PATH = "trains.json"; // Changed to relative path for resources
    private ObjectMapper objectMapper = new ObjectMapper();

    public TrainService() {
        try {
            // Load from resources
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(TRAINS_PATH);
            if (inputStream != null) {
                trainList = objectMapper.readValue(inputStream, new TypeReference<List<Train>>() {});
                inputStream.close();
            } else {
                trainList = new ArrayList<>();
                System.err.println("trains.json not found in resources. Initializing with empty list.");
            }
        } catch (Exception e) {
            trainList = new ArrayList<>();
            System.err.println("Error loading trains.json: " + e.getMessage());
        }
    }

    public List<Train> searchTrains(String source, String destination) {
        return trainList.stream()
                .filter(train -> validTrain(train, source, destination))
                .collect(Collectors.toList());
    }

    public Train getTrainByNo(String trainNo) {
        return trainList.stream()
                .filter(train -> train.getTrainNo().equals(trainNo))
                .findFirst()
                .orElse(null);
    }

    private boolean validTrain(Train train, String source, String destination) {
        List<String> stations = train.getStations();
        return stations != null && stations.contains(source) && stations.contains(destination)
            && stations.indexOf(source) < stations.indexOf(destination);
    }
}
