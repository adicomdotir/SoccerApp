package ir.adicom.app.soccerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import ir.adicom.app.soccerapp.models.Position;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(this);
        RuntimeExceptionDao<Position, String> simpleDao = db.getSimpleDataDao();
        // query for all of the data objects in the database
        List<Position> list = simpleDao.queryForAll();
        // our string builder for building the content-view
        StringBuilder sb = new StringBuilder();
        sb.append("Found ").append(list.size()).append(" entries in DB in ").append("()\n");

        // if we already have items in the database
        int simpleC = 1;
        for (Position simple : list) {
            sb.append('#').append(simpleC).append(": ").append(simple.getName()).append('\n');
            simpleC++;
        }
        sb.append("------------------------------------------\n");
        Log.e(LOG_TAG, sb.toString());
    }
}
