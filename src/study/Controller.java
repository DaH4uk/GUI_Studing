package study;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Controller {
    public ComboBox cmbFormat;
    public TextArea textURL;
    public ImageView imageView;
    public static Stage STAGE;
    public static BufferedImage image;
    public URL url = null;



    public static BufferedImage getImage(URL url)
    {
        return image;
    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
    }

    public static void saveImage(File file,String format)
    {
        try {
            ImageIO.write(image, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImage(URL url)
    {
        try {
            setImage(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImage(File file)
    {
        try {
            setImage(ImageIO.read(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnView()
    {
        Image imView = SwingFXUtils.toFXImage(image, null);
        imageView.setImage(imView);
    }
    public void btnGetFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        File file = fileChooser.showOpenDialog(STAGE);
        if (file != null) {
            setImage(file);
        }
    }

    public void btnGetImage()
    {
        try {
            url = new URL(textURL.getText());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        setImage(url);
    }

    public void save()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image File");
        FileChooser.ExtensionFilter extFilter = null;
        if (cmbFormat.getValue().equals("png"))
        {
            extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        } else {
            extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        }
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(STAGE);
        if (file != null)
        {
            saveImage(file, (String) cmbFormat.getValue());
        }

    }
    public void exit()
    {
        STAGE.close();
    }
}
