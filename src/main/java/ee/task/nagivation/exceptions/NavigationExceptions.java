package ee.task.nagivation.exceptions;

public interface NavigationExceptions {
    String INVALID_BASE_STATION_ID = "Base station with given id does not exist";
    String NOT_DETECTED = "Mobile station position is yet to be detected";
    String APPROXIMATE_POSITION = "Due to lack of data, mobile station position is approximate within error radius";
}
