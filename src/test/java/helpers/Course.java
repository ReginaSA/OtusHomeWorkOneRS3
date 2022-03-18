package helpers;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Course {
    String name;
    String link;
    String rawDate;
    LocalDate timeStartBegin;

    public LocalDate getTimeStartBegin() {
        return timeStartBegin;
    }

    public Course(String name, String link, String rawDate){
        this.name = name;
        this.link = link;
        this.rawDate = rawDate;
        this.timeStartBegin = this.convertDateToFormat(rawDate);
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getRawDate() {
        return rawDate;
    }

    private LocalDate convertDateToFormat(String rawDate) {

        int year = LocalDate.now().getYear();
        String newRawDate = rawDate.replace("С ", "");
        Locale russian = new Locale("ru");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM", russian);
        LocalDate localDate = MonthDay.parse(newRawDate, formatter).atYear(year);

        return localDate;
    }
}
