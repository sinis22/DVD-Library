package UI;

import DTO.DVDLibrary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DVDLibraryView {
    private final UserIO io;

    //Display the menu to the user.
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List All DVDs");
        io.print("2. Create New DVDLibrary");
        io.print("3. View a DVDLibrary");
        io.print("4. Remove a DVDLibrary");
        io.print("5. Edit a DVDLibrary");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    //Allow the user to enter information about the DVD

    public DVDLibrary getNewDVD() {
        String title = io.readString("Please enter DVDLibrary Title");
        String ReleaseDate = io.readString("Please enter Release date in format DD/MM/YYYY");
        String MPAARating = io.readString("Please enter MPAA Rating");
        String DirectorName = io.readString("Please enter Director Name");
        String Studio = io.readString("Please enter Studio");
        String Note = io.readString("Please enter a Note");
        DVDLibrary currentDVD = new DVDLibrary();
        currentDVD.setTitle(title);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date =  LocalDate.parse(ReleaseDate, format);
        currentDVD.setReleaseDate(date);
        currentDVD.setMPAARating(MPAARating);
        currentDVD.setDirectorName(DirectorName);
        currentDVD.setStudio(Studio);
        currentDVD.setNote(Note);
        return currentDVD;
    }
    //Display a new message for creating a DVD
    public void displayCreateDVDBanner() {
        io.print("=== Create DVDLibrary ===");
    }

    //Message to show that DVD creation has been successful.
    public void displayCreateSuccessBanner() {
        io.readString(
                "DVDLibrary successfully created.  Please hit enter to continue");
    }

    //Display all DVDs and their information from the library
    public void displayDVDList(List<DVDLibrary> DVDList) {
        for (DVDLibrary currentDVD : DVDList) {
            String DVDInfo = String.format("%s : %s",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate());
            io.print(DVDInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    //Show that the DVDs are being displayed.
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    //Show that the DVDs are being displayed.

    public void displayDisplayDVDBanner() {
        io.print("=== Display DVDLibrary ===");
    }

    //Asking for user input to get DVD by title
    public String getDVD() {
        return io.readString("Please enter the DVDLibrary Title.");
    }

    //Show the particular DVD information asked by the user by the title
    public void displayDVD(DVDLibrary DVD) {
        if (DVD != null) {
            io.print("XXXXXXXXXXXXXXXXXX");
            io.print("Title: " + DVD.getTitle());
            io.print("Release date: " + DVD.getReleaseDate());
            io.print("MPAA Rating: " + DVD.getMPAARating());
            io.print("Director's Name: " + DVD.getDirectorName());
            io.print("Studio: " + DVD.getStudio());
            io.print("Note: " + DVD.getNote());
            io.print("XXXXXXXXXXXXXXXXXX");
        } else {
            io.print("No such DVDLibrary.");
        }
        io.readString("Please hit enter to continue.");
    }

    //Show that the DVDs are being displayed.
    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVDLibrary ===");
    }

    //Display the result of the DVD being removed from the library
    public void displayRemoveResult(DVDLibrary DVDRecord) {
        if(DVDRecord != null){
            io.print("DVDLibrary successfully removed.");
        }else{
            io.print("No such DVDLibrary.");
        }
        io.readString("Please hit enter to continue.");
    }

    //Show that the DVDs are being displayed.
    public void displayEditDVDBanner() {
        io.print("=== Edit DVDLibrary ===");
    }

    //Ask the user to select a category to edit
    public String getCategory() {
        io.print("Please enter the category that you want to change (title, release date, director name, MPAA rating, studio or note:");
        io.print("title");
        io.print("release date");
        io.print("director name");
        io.print("MPAA rating");
        io.print("studio");
        io.print("or note:");
        return io.readString();
    }

    //Request what they would like the new information to be
    public String getUpdate() {
        io.print("Please enter your new change that you'd like to make");
        return io.readString();
    }

    //Display to show if the update was successful
    public void displayEditResultBanner(DVDLibrary dvd) {

        if(dvd != null) {
            io.print("DVDLibrary information was changed successfully");
        }
        else {
            io.print("The Edit was unsuccessful, please make sure that the correct DVDLibrary title and correct category was typed" );
        }
    }
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

}
