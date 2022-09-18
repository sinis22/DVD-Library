import CONTROLLER.DVDLibraryController;
import DAO.DVDLibraryDao;
import DAO.DVDLibraryDaoException;
import DAO.DVDLibraryDaoFileImpl;
import UI.DVDLibraryView;
import UI.UserIO;
import UI.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) throws DVDLibraryDaoException {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }
}
