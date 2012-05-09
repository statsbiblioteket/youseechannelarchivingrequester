package dk.statsbiblioteket.mediaplatform.ingest.channelarchivingrequester;

/**
 *
 */
public enum Schedule {
    DAILY("Daily"), MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY("Thursday"), FRIDAY("Friday"),
    SATURDAY("Saturday"), SUNDAY("Sunday"), MONDAY_TO_THURSDAY("Monday-Thursday"), MONDAY_TO_FRIDAY("Monday-Friday"), SATURDAY_AND_SUNDAY("Saturday-Sunday"),  ;

    String description;

    Schedule(String name) {
       this.description = name;
    }

    public String getDescription() {
        return description;
    }
}
