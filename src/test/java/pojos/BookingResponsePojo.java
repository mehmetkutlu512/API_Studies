package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponsePojo {

    private BookingPojo booking;

    public BookingResponsePojo(BookingPojo booking) {
        this.booking = booking;
    }

    public BookingResponsePojo() {
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "booking=" + booking +
                '}';
    }
}
