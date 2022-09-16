//package DTO;
//
//public class UserRating {
//
//    private int rating;
//    private String note = "";
//
//    public UserRating() {
//
//    }
//    public UserRating(String note) {
//        this.note = note;
//    }
//
//    public UserRating(int rating) {
//        this.rating = rating;
//    }
//
//    public UserRating(int rating, String note) {
//        this.rating = rating;
//        this.note = note;
//    }
//
//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }
//
//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(String note) {
//        this.note = note;
//    }
//
//    public String toString() {
//        if (rating != 0 && !note.isEmpty()) {
//            return rating + "/10 - " + note;
//        } else if (rating == 0 && !note.isEmpty()) {
//            return note;
//        } else if (rating != 0) {
//            return rating + "/10";
//        } else return "No user rating given.";
//    }
//}
