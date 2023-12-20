package news_paper;

public class Date {
    // private is the default so no need to specify
    int day; // the day, i.e. the 25th will be 25
    int month; // month, i.e. november will be 11
    int year; // year, i.e. 2015

    /**
     * sets all attributes to the given params
     * @param day the day
     * @param month the month
     * @param year the year
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * sets all attributes to the given date's attributes
     * @param date another date
     */
    public Date(Date date) {
        this.day = date.getDay();
        this.month = date.getMonth();
        this.year = date.getYear();
    }

    /**
     * sets a new day
     * @param day new day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * set a new month
     * @param month a new month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * set a new year
     * @param year a new year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * get the day
     * @return the day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * get month
     * @return month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * get year
     * @return year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * format date with the format [day].[month].[year]
     * @return formatted string
     */
    public String toString() {
        return this.day + "." + this.month + "." + this.year;
    }

    /**
     * compares 2 dates
     * @param d another date
     * @return true if d happened before this
     */
    public boolean isOlder(Date d) {
        if (d.getYear() < this.year) {
            return true;
        } else if (d.getYear() > this.year) {
            return false;
        } else if (d.getMonth() < this.month) {
            return true;
        } else if (d.getMonth() > this.month) {
            return false;
        } else return d.getDay() < this.day;
    }
}
