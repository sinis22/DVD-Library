package Controller;

import DAO.DVD_Library_DAO;
import DAO.DVD_Library_DAO_Exception;
import DTO.DVD;
import UI.DVD_Library_View;
import UI.UserIO;
import UI.UserIOConsoleImpl;

public class DVD_Library_Controller {
	
	private DVD_Library_View view;
	private DVD_Library_DAO dao;
	
	private UserIO io = new UserIOConsoleImpl();
	
	public DVD_Library_Controller(DVD_Library_View view, DVD_Library_DAO dao) {
		this.view = view;
		this.dao = dao;
	}
	
	//This is where the controller begins executing
	public void run() {
		boolean keepGoing = true;
		int userSelection = 0;
		
		try {
		while(keepGoing) {
			
			//Get the selection from user
			userSelection = getMenuSelection();
			
			switch(userSelection) {
				
			case 1:
				createDVD();
				break;
			case 2:
				removeDVD();
				break;
			case 3:
				editDVD();
				break;
			case 4:
				displayDVDs();
				break;
			case 5:
				displayDVDByTitle();
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
		catch(DVD_Library_DAO_Exception e) {
			view.displayErrorMessage(e.getMessage());
		}
	}
	
	//Receive menu selection from user
	private int getMenuSelection() {
		return view.printMenuAndGetSelection();
	}
	
	//Create a new DVD
	private void createDVD() throws DVD_Library_DAO_Exception{
		view.displayCreateDVDBanner();
		DVD newDVD = view.getNewDVDInfo();
		dao.addDVD(newDVD.getTitle(), newDVD);
		view.displayCreateDVDSuccessBanner();
	}
	
	//Display all DVDs in the collection
	private void displayDVDs() throws DVD_Library_DAO_Exception{
		view.displayDisplayAllBanner();
		view.displayDVDList(dao.getAllDVDs());
		view.displayDisplayAllSuccessBanner();
	}
	
	//Display DVD by title
	private void displayDVDByTitle() throws DVD_Library_DAO_Exception{
		view.displayDisplayDVDBanner();
		String DVDtitle = view.DVDTitleRequest();
		DVD tempDVD = view.displayDVD(dao.getDVD(DVDtitle));
		view.displayDisplayDVDSuccessBanner(tempDVD);
	}
	
	//Remove a particular DVD
	private void removeDVD() throws DVD_Library_DAO_Exception{
		String title = view.DVDTitleRequest();
		DVD dvd = dao.removeDVD(title);
		view.removeDVDSuccessBanner(dvd);
	}
	
	//Edit a particular DVD
	private void editDVD() throws DVD_Library_DAO_Exception{
		
		String title = view.DVDTitleRequest();
		String pieceOfInformation = view.getPieceOfInformation();
		String change = view.getChange();
		DVD dvd = dao.editDVDInformation(title, pieceOfInformation, change);
		view.displayMakeChangeSuccessBanner(dvd);
	}
	
	//Tell the user that the message is unknown
	private void unknownCommand() {
		view.displayUnknownCommandMessage();
	}
	
	//Tell the user that the program has ended
	private void exitMessage() {
		view.displayExitMessage();
	}
	
}
