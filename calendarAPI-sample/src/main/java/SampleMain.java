public class SampleMain {
    public static void main(String[] args) {
        // Show events on user's calendar.
        View.header("Show Calendars");
        CalendarList feed = client.calendarList().list().execute();
        View.display(feed);
    }
}
