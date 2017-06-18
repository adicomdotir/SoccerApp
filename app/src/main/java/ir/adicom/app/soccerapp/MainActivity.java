package ir.adicom.app.soccerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import ir.adicom.app.soccerapp.models.Continent;
import ir.adicom.app.soccerapp.models.Country;
import ir.adicom.app.soccerapp.models.Position;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(this);
        RuntimeExceptionDao<Continent, Integer> continentDao = db.getRuntimeExceptionDao(Continent.class);
        RuntimeExceptionDao<Country, Integer> simpleDao = db.getRuntimeExceptionDao(Country.class);

        QueryBuilder<Continent, Integer> continentQueryBuilder = continentDao.queryBuilder();
        QueryBuilder<Country, Integer> countryQueryBuilder = simpleDao.queryBuilder();
        try {
            countryQueryBuilder.join(continentQueryBuilder);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // query for all of the data objects in the database
        List<Country> list = null;
        try {
            list = countryQueryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // our string builder for building the content-view
        StringBuilder sb = new StringBuilder();
        sb.append("Found ").append(list.size()).append(" entries in DB in ").append("()\n");
        // if we already have items in the database
        int simpleC = 1;
        for (Country simple : list) {
            sb.append('#').append(simpleC).append(": ").append(simple).append('\n');
            simpleC++;
        }
        sb.append("------------------------------------------\n");
        Log.e(LOG_TAG, sb.toString());
    }
}
