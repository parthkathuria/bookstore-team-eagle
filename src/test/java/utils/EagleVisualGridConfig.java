package utils;

import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;

public class EagleVisualGridConfig {

    public static Configuration getGrid(String appName) {
        Configuration vgConfig = new Configuration();
        vgConfig.setAppName(appName);

        //Browsers
        vgConfig.addBrowser(800, 600, BrowserType.FIREFOX);
        vgConfig.addBrowser(800, 600, BrowserType.CHROME);
        vgConfig.addBrowser(800, 600, BrowserType.IE_11);
        vgConfig.addBrowser(1200, 800, BrowserType.FIREFOX);
        vgConfig.addBrowser(1200, 800, BrowserType.CHROME);
        vgConfig.addBrowser(1200, 800, BrowserType.IE_10);

        //Devices
        vgConfig.addDeviceEmulation(DeviceName.iPhone_X);
        vgConfig.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.LANDSCAPE);
        vgConfig.addDeviceEmulation(DeviceName.Galaxy_S5);
        vgConfig.addDeviceEmulation(DeviceName.Galaxy_S5, ScreenOrientation.LANDSCAPE);

        return vgConfig;
    }

}