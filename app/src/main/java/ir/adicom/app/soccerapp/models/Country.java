package ir.adicom.app.soccerapp.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * Created by adicom on 6/17/17.
 */

@DatabaseTable(tableName = "countries")
public class Country {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, columnName = "continent_id")
    private Continent continent;
    @DatabaseField
    private String name;

    public Country() {}

    public Country(Continent continent, String name) {
        this.continent = continent;
        this.name = name;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[" + id + "," + name + "," + continent + "]";
    }
}
