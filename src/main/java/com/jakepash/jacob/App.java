package com.jakepash.jacob;


import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.DailyDataPoint;
import tk.plogitech.darksky.forecast.model.Forecast;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

import javax.swing.*;
import java.awt.*;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.jakepash.jacob.Feed;
import com.jakepash.jacob.FeedMessage;
import com.jakepash.jacob.RSSFeedParser;


/**
 *
 * @author jacobpashman
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ForecastException {
        // TODO code application logic here

        new App();
    }

    public App() throws ForecastException {

        JFrame guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Example GUI");
        guiFrame.setSize(700, 1000);
        guiFrame.getContentPane().setBackground(Color.WHITE);
        guiFrame.setLocationRelativeTo(null);





        JLabel timeLbl = new JLabel(getTime());
        guiFrame.add(timeLbl, BorderLayout.NORTH);

        ImagePanel icon = new ImagePanel(Toolkit.getDefaultToolkit().createImage(getForcast().getIcon()));
        System.out.println("icon" + getForcast().getIcon());
        icon.setPreferredSize(new Dimension(100,100));
        guiFrame.add(icon, BorderLayout.CENTER);


        guiFrame.setVisible(true);
        System.out.println(getForcast());


        RSSFeedParser parser = new RSSFeedParser(
                "http://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }

    }

    private String getTime() {

        DateFormat df = new SimpleDateFormat("hh:mm a");
        Date dateobj = new Date();
        System.out.println(df.format(dateobj));

        return df.format(dateobj);

    }


    private Weather getForcast() throws ForecastException {

        ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey("3424af03fab2573826627ec30f357a14"))
                .units(ForecastRequestBuilder.Units.us)
                .language(ForecastRequestBuilder.Language.en)
                .location(new GeoCoordinates(new Longitude(-122.539), new Latitude(37.911))).build();

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        Forecast forecast = client.forecast(request);



        Weather weather = new Weather();
        weather.setTemp(getTemp(forecast));
        weather.setIcon(getIcon(forecast));
        weather.setMinTemp(getMinTemp(forecast));
        weather.setMaxTemp(getMaxTemp(forecast));

        return weather;
    }

    private int getTemp(Forecast forecast) {
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        double currentTemp = (forecast.getCurrently().getTemperature());
        System.out.println(df.format(currentTemp));
        return Integer.parseInt(df.format(currentTemp));
    }

    private String getIcon(Forecast forecast) {
        String icon = forecast.getDaily().getIcon();
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir + "/src/main/graphics/" + icon + ".gif");
        return userDir + "/src/main/graphics/" + icon + ".gif";
    }

    private int getMinTemp(Forecast forecast) {
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        double minTemp = (forecast.getDaily().getData().get(0).getTemperatureMin());
        System.out.println(df.format(minTemp));
        return Integer.parseInt(df.format(minTemp));
    }

    private int getMaxTemp(Forecast forecast) {
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        double maxTemp = (forecast.getDaily().getData().get(0).getTemperatureMax());
        System.out.println(df.format(maxTemp));
        return Integer.parseInt(df.format(maxTemp));
    }







}


class ImagePanel extends JPanel {

    Image image;

    public ImagePanel(Image imageInput) {
            image = imageInput;
//            image = Toolkit.getDefaultToolkit().createImage("/Users/jacobpashman/Documents/mirror/src/main/graphics/clear-day.gif");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }

}
