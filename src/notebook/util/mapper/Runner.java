package notebook.util.mapper;

import notebook.controller.UserController;
import notebook.model.repository.GBRepository;
import notebook.model.repository.impl.UserRepository;
import notebook.view.UserView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static notebook.util.DBConnector.DB_PATH;
import static notebook.util.DBConnector.createDB;

public class Runner {

    private Runner() {
        createDB();
        GBRepository repository = new UserRepository();
        UserController controller = new UserController(repository);
        UserView view = new UserView(controller);
        view.run();
    }

    public static void run() {
        new Runner();
    }
}



