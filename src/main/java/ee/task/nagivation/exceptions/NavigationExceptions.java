package ee.task.nagivation.exceptions;

public interface NavigationExceptions {
    String INVALID_BASE_ID = "Base station with given id does not exist";
    String MOBILE_NOT_DETECTED = "Mobile station position is yet to be detected";
    String INVALID_DETECTION_DISTANCE = "Detection distance cannot exceed base station detection radius";

}
