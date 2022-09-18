package DAO;

import DTO.DVDLibrary;

import java.util.List;

public interface DVDLibraryDao {

    void addDVD(String title, DVDLibrary dvd) throws DVDLibraryDaoException;

    List<DVDLibrary> getAllDVDs() throws DVDLibraryDaoException;

    DVDLibrary getDVD(String title) throws DVDLibraryDaoException;

    DVDLibrary removeDVD(String title) throws DVDLibraryDaoException;

    DVDLibrary editDVD(String title, String pieceOfInformation, String change) throws DVDLibraryDaoException;

}
