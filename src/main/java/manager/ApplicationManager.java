package manager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
   // WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;
    String browser;
    WebDriverWait wait;
    HelperSearch helperSearch;

    Properties properties;

    public String getEmail(){
        return properties.getProperty("web.email");
    }
    public String getPassword(){
        return properties.getProperty("web.password");
    }

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public WebDriverWait getWait() {return wait;}
    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar getHelperCar() {
        return helperCar;
    }

    public HelperSearch getHelperSearch() {
        return helperSearch;
    }

    @BeforeSuite
    public void init() throws IOException {
        //L16 Add properties to the project
  //      properties.load(new FileReader(new File("src/test/resources/prod.properties")));
//         target - what file we want to use?
        String target = System.getProperty("target", "prod");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
     //   wd = new ChromeDriver();
        if (browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Tests Using Chrome");
        } else if (browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Tests Using Firefox");
        } else if (browser.equals(BrowserType.EDGE)){
            wd = new EventFiringWebDriver(new EdgeDriver());
            logger.info("Tests Using Edge");
        }
        wd.register(new WebDriverListener()); // connection LISTENER
        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);
        helperSearch = new HelperSearch(wd);
     //   wd.manage().window().maximize();
      //  wd.navigate().to("https://ilcarro.web.app/search");
        wd.navigate().to(properties.getProperty("web.baseURL"));
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(wd, 90);
        // make this wait TODO
    }

    public void navigateToMainPage() {
        wd.navigate().to(properties.getProperty("web.baseURL"));
    }

    @AfterSuite
    public void tearDown(){
      wd.quit();
    }
}
