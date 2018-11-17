package myshop.com.myshop.model;

/**
 * Created by Admin on 17/11/2018.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Item {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "inv_mast_uid")
    private String invMastUid;

    @ColumnInfo(name ="item_id")
    private String itemId;

    @ColumnInfo(name ="item_desc")
    private String itemDesc;

    @ColumnInfo(name ="unit")
    private String unit;

    @ColumnInfo(name ="image")
    private String image;

    @ColumnInfo(name ="notes")
    private String notes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvMastUid() {
        return invMastUid;
    }

    public void setInvMastUid(String invMastUid) {
        this.invMastUid = invMastUid;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
