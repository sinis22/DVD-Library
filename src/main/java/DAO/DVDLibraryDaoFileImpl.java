package DAO;

import DTO.DVDLibrary;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    private final Map<String, DVDLibrary> library = new HashMap<>();
    private final  String DELIMITER = ",";
    private final String file = "DVDLibrary.txt";

    @Override
    //Add a  DVD by title
    public void addDVD(String title, DVDLibrary DVD) throws DVDLibraryDaoException {
        ReadDVD();
        this.library.put(title, DVD);
        WriteDVD();
    }

    @Override
    //Retrieve all DVDs in the Library
    public List<DVDLibrary> getAllDVDs() throws DVDLibraryDaoException {
        ReadDVD();
        return new ArrayList<>(library.values());
    }

    @Override
    //Get a  DVD from the Library
    public DVDLibrary getDVD(String title) throws DVDLibraryDaoException {
        ReadDVD();
        return library.get(title);
    }

    @Override
    //Remove a particular DVD by title
    public DVDLibrary removeDVD(String title) throws DVDLibraryDaoException {
        ReadDVD();
        DVDLibrary removedDVD = library.remove(title);
        WriteDVD();
        return removedDVD;
    }

    @Override
    //Edit information about the DVD
    public DVDLibrary editDVD(String title, String pieceOfInformation, String change) throws DVDLibraryDaoException {
        ReadDVD();
        DVDLibrary dvd = library.get(title);

        if(dvd != null) {
            if(pieceOfInformation.equalsIgnoreCase("Title")) {
                dvd.setTitle(change);
            }
            else if(pieceOfInformation.equalsIgnoreCase("Release date")) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date =  LocalDate.parse(change, format);
                dvd.setReleaseDate(date);
            }
            else if(pieceOfInformation.equalsIgnoreCase("MPAA rating")) {
                dvd.setMPAARating(change);
            }
            else if(pieceOfInformation.equalsIgnoreCase("Director name")) {
                dvd.setDirectorName(change);
            }
            else if(pieceOfInformation.equalsIgnoreCase("Studio")) {
                dvd.setStudio(change);
            }
            else if(pieceOfInformation.equalsIgnoreCase("Note")) {
                dvd.setNote(change);
            }
            else {
                dvd = null;
            }
        }
        if(dvd != null) library.replace(title, dvd);
        WriteDVD();
        return dvd;

    }

    //Add all unmarshalled data into the library
    private void ReadDVD() throws DVDLibraryDaoException {
        Scanner scanner;
        String currentLine;
        DVDLibrary currentDVD;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(file)));
        }
        catch(FileNotFoundException e) {
            throw new DVDLibraryDaoException("Could not locate the file", e);
        }
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            library.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }

    //Write all marshalled data into the textfile
    private void WriteDVD() throws DVDLibraryDaoException {
        PrintWriter out;
        String dvd;

        try {
            out = new PrintWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not save DVDLibrary data",e);
        }

        List <DVDLibrary> dvdList = this.getAllDVDs();
        for (DVDLibrary currentDvd : dvdList) {
            dvd = marshallDvd(currentDvd);
            out.println(dvd);
            out.flush();
        }
        out.close();
    }

    //converting XML content to Java objects
    private DVDLibrary unmarshallDVD(String dvd) {

        String [] dvdTokens = dvd.split(DELIMITER);
        String title = dvdTokens[0];
        String releaseDate = dvdTokens[1];
        String mpaaRating = dvdTokens[2];
        String directorName = dvdTokens[3];
        String studio = dvdTokens[4];
        String note = dvdTokens[5];


        DVDLibrary dvdFromFile = new DVDLibrary();
        dvdFromFile.setTitle(title);
        dvdFromFile.setReleaseDate(LocalDate.parse(releaseDate));
        dvdFromFile.setMPAARating(mpaaRating);
        dvdFromFile.setDirectorName(directorName);
        dvdFromFile.setStudio(studio);
        dvdFromFile.setNote(note);
        return dvdFromFile;
    }

    //writing Java objects to XML file
    private String marshallDvd(DVDLibrary dvd) {
        String dvdAsText = dvd.getTitle() + DELIMITER;
        dvdAsText += dvd.getReleaseDate() + DELIMITER;
        dvdAsText += dvd.getMPAARating() + DELIMITER;
        dvdAsText += dvd.getDirectorName() + DELIMITER;
        dvdAsText += dvd.getStudio() + DELIMITER;
        dvdAsText += dvd.getNote();
        return dvdAsText;
    }
}
