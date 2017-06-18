package ir.adicom.app.soccerapp.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * Created by adicom on 6/17/17.
 */

@DatabaseTable(tableName = "continents")
public class Continent {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField()
    private String name;

    public Continent() {}
    public Continent(String name) {
        this.name = name;
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
        return "[" + id + "," + name + "]";
    }
}
