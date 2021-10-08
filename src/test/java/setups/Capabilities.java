package setups;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class Capabilities extends DesiredCapabilities {
    protected AndroidDriver androidDriver;
    final String dir = System.getProperty("user.dir")+"\\choco.apk";
    DesiredCapabilities capabilities = new DesiredCapabilities();

    protected void setupCapabilities() throws Exception {
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "11");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("appPackage", "app.choco.dummyqa");
        capabilities.setCapability("appActivity", "app.choco.dummyqa.SplashScreenActivity");
        capabilities.setCapability(MobileCapabilityType.APP, dir);
    }
    protected AndroidDriver launchApp() throws MalformedURLException {
        androidDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        androidDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return androidDriver;
    }
    public void closeDriver(){
        if(androidDriver!=null) {
            androidDriver.closeApp();
        }
    }
}