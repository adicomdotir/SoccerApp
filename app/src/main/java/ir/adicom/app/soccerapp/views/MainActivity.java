package ir.adicom.app.soccerapp.views;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import ir.adicom.app.soccerapp.R;
import ir.adicom.app.soccerapp.presenters.IPresenter;
import ir.adicom.app.soccerapp.presenters.Presenter;

public class MainActivity extends AppCompatActivity implements IView {

    private static final String LOG_TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        DatabaseHelper db = new DatabaseHelper(this);
        RuntimeExceptionDao<Continent, Integer> continentDao = db.getRuntimeExceptionDao(Continent.class);
        RuntimeExceptionDao<Country, Integer> simpleDao = db.getRuntimeExceptionDao(Country.class);
        QueryBuilder<Continent, Integer> continentQueryBuilder = continentDao.queryBuilder();
        QueryBuilder<Country, Integer> countryQueryBuilder = simpleDao.queryBuilder();
        try {
            continentQueryBuilder.join(countryQueryBuilder);
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
        */

        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/splash.ttf");
        TextView tvHeader = (TextView) findViewById(R.id.tvHeader);
        if (tvHeader != null) tvHeader.setTypeface(font);
        IPresenter presenter = new Presenter(this);
    }

    @Override
    public void setGreeting(@NonNull String greeting) {
        Toast.makeText(this, greeting, Toast.LENGTH_SHORT).show();
    }
}
