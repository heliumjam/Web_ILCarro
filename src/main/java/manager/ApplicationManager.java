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

    public ApplicationManager(String browser) {
        this.browser = browser;
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
    public void init(){
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
        wd.navigate().to("https://ilcarro.web.app/search");
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(wd, 90);
        // make this wait TODO
    }


    @AfterSuite
    public void tearDown(){
    //  wd.quit();
    }
}
