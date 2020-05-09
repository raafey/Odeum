package domain;

public class Seat {
    private String seatId;
    private String col;
    private String row;
    private String isBooked;
    private String userId;
    private String schId;
    
    public Seat(String seatId, String row, String col, String schId, String isBooked, String userId) {
        this.seatId = seatId;
        this.col = col;
        this.row = row;
        this.isBooked = isBooked;
        this.userId = userId;
        this.schId = schId;
    }

    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(String isBooked) {
        this.isBooked = isBooked;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
}
