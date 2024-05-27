package learn.foraging;

import learn.foraging.data.ForageFileRepository;
import learn.foraging.data.ForagerFileRepository;
import learn.foraging.data.ItemFileRepository;
import learn.foraging.domain.ForageService;
import learn.foraging.domain.ForagerService;
import learn.foraging.domain.ItemService;
import learn.foraging.ui.ConsoleIO;
import learn.foraging.ui.Controller;
import learn.foraging.ui.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
@ComponentScan
@PropertySource("classpath:data.properties")
public class App {
    public static void main(String[] args) {

//        ConsoleIO io = new ConsoleIO();
//        View view = new View(io);
//
//        ForageFileRepository forageFileRepository = new ForageFileRepository("./data/forage_data");
//        ForagerFileRepository foragerFileRepository = new ForagerFileRepository("./data/foragers.csv");
//        ItemFileRepository itemFileRepository = new ItemFileRepository("./data/items.txt");
//
//        ForagerService foragerService = new ForagerService(foragerFileRepository);
//        ForageService forageService = new ForageService(forageFileRepository, foragerFileRepository, itemFileRepository);
//        ItemService itemService = new ItemService(itemFileRepository);
//
//        Controller controller = new Controller(foragerService, forageService, itemService, view);
//        controller.run();
        ApplicationContext context= new AnnotationConfigApplicationContext(App.class);
        Controller controller = context.getBean(Controller.class);
        controller.run();
    }
}
