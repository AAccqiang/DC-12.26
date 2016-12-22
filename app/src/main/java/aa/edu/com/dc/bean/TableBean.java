package aa.edu.com.dc.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

@DatabaseTable(tableName = "table_list")
public class TableBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "table_num")
    private int num;
    @DatabaseField(columnName = "table_name")
    private String tablename;
    @DatabaseField(columnName = "table_size")
    private int number;
    @DatabaseField(columnName = "table_add")
    private String description;
    @DatabaseField(columnName = "staff_num")
    private int version;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TableBean(int num, String tablename, int number) {

        this.num = num;
        this.tablename = tablename;
        this.number = number;

    }

    public TableBean() {

    }
}
