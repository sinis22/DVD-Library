package controller;

import dao.DVDLibraryDao;
import dao.DVDLibraryDaoException;
import dto.DVDLibrary;
import ui.DVDLibraryView;

import java.util.List;


public class DVDLibraryController {
    private final DVDLibraryView view;
    private final DVDLibraryDao dao;

    public void run() throws DVDLibraryDaoException {
        boolean keepGoing = true;
        int menuSelection;
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listDVDs();
                    break;
                case 2:
                    createDVD();
                    break;
                case 3:
                    viewDVD();
                    break;
                case 4:
                    removeDVD();
                    break;
                case 5:
                    editDVD();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVDLibrary newDVD = view.getNewDVD();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVDLibrary> DVDList = dao.getAllDVDs();
        view.displayDVDList(DVDList);
    }

    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String title = view.getDVD();
        DVDLibrary DVD = dao.getDVD(title);
        view.displayDVD(DVD);
    }

    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String title = view.getDVD();
        DVDLibrary removedDVD = dao.removeDVD(title);
        view.displayRemoveResult(removedDVD);
    }
    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
        String title = view.getDVD();
        String pieceOfInformation = view.getCategory();
        String change = view.getUpdate();
        DVDLibrary dvd = dao.editDVD(title, pieceOfInformation, change);
        view.displayEditResultBanner(dvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

}


