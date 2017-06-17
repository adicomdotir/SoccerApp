package ir.adicom.app.soccerapp.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * Created by adicom on 6/16/17.
 */

@DatabaseTable(tableName = "positions")
public class Position {


    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;

    public Position() {
        // ORMLite needs a no-arg constructor
    }
    public Position(String name) {
        this.id = id;
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
}
