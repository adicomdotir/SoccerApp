package ir.adicom.app.soccerapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import ir.adicom.app.soccerapp.models.Continent;
import ir.adicom.app.soccerapp.models.Country;
import ir.adicom.app.soccerapp.models.Position;

/**
 *
 * Created by adicom on 5/26/17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "soccerdb.db";
    private static final int DATABASE_VERSION = 7;

    // the DAO object we use to access the tables
    private Dao<Position, Integer> simpleDao = null;
    private RuntimeExceptionDao<Position, String> simpleRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Position.class);
            TableUtils.createTable(connectionSource, Continent.class);
            TableUtils.createTable(connectionSource, Country.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        // here we try inserting data in the on-create
        RuntimeExceptionDao<Position, String> dao = getSimpleDataDao();
        RuntimeExceptionDao<Continent, String> daoContinent = getRuntimeExceptionDao(Continent.class);
        RuntimeExceptionDao<Country, String> countryDao = getRuntimeExceptionDao(Country.class);
        long millis = System.currentTimeMillis();

        // create some entries in the onCreate
        dao.create(new Position("Goalkeeper"));
        dao.create(new Position("Defender"));
        dao.create(new Position("Midfielder"));
        dao.create(new Position("Forward"));

        Continent asia = new Continent("Asia");
        daoContinent.create(asia);
        daoContinent.create(new Continent("Africa"));
        daoContinent.create(new Continent("North America"));
        daoContinent.create(new Continent("South America"));
        daoContinent.create(new Continent("Europe"));
        daoContinent.create(new Continent("Australia"));

        countryDao.create(new Country(asia, "Iran"));
        countryDao.create(new Country(asia, "Iraq"));
        countryDao.create(new Country(asia, "China"));

        Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: " + millis);
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Position.class, true);
            TableUtils.dropTable(connectionSource, Country.class, true);
            TableUtils.dropTable(connectionSource, Continent.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our Postion class. It will create it or just give the cached
     * value.
     */
    public Dao<Position, Integer> getDao() throws SQLException, java.sql.SQLException {
        if (simpleDao == null) {
            simpleDao = getDao(Position.class);
        }
        return simpleDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our Postion class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Position, String> getSimpleDataDao() {
        if (simpleRuntimeDao == null) {
            simpleRuntimeDao = getRuntimeExceptionDao(Position.class);
        }
        return simpleRuntimeDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        simpleDao = null;
        simpleRuntimeDao = null;
    }
}