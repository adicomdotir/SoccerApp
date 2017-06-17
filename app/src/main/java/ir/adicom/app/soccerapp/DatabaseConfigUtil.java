package ir.adicom.app.soccerapp;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;

import ir.adicom.app.soccerapp.models.Continent;
import ir.adicom.app.soccerapp.models.Position;

/**
 *
 * Created by adicom on 5/26/17.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[] {
            Position.class, Continent.class
    };

    public static void main(String[] args) {
        try {
            writeConfigFile(new File("/home/adicom/AndroidStudioProjects/android_mvp/SoccerApp/app/src/main/res/raw/ormlite_config.txt"), classes);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
