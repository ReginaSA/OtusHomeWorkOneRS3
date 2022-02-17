package helpers;

public class Course {
    String name;
    String link;
    String rawDate;
    long timeStart;
    static long timeStartBegin = 100000000;

    public Course(String name, String link, String rawDate) {
        this.name = name;
        this.link = link;
        this.rawDate = rawDate;
        this.timeStart = this.rawDateToTime(rawDate);
    }

    public enum CourseFields {
        name,
        link,
        startDate
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRawDate() {
        return rawDate;
    }

    public void setRawDate(String rawDate) {
        this.rawDate = rawDate;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    public static long getTimeStartBegin() {
        return timeStartBegin;
    }

    public static void setTimeStartBegin(long timeStartBegin) {
        Course.timeStartBegin = timeStartBegin;
    }



    private long rawDateToTime(String rawDate) {
        timeStartBegin += 2000 + rawDate.length();

        return timeStartBegin;
    }
}
