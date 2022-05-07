package com.flonotes.Data.Repository.Domain;


import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



@Entity
@Data
@Table(name="StickyNote")
public class StickyNoteDomain implements Serializable {
    @Id
    @Column(name="noteUUID",unique = true)
   // @Type(type = "uuid-char")
    public String noteUUID;

    public String getnoteUUID() {
        return noteUUID;
    }

    public void setnoteUUID(String noteUUID) {
        this.noteUUID = noteUUID;
    }

    @Column(name="status")
    private String status;

    @Column(name="notetext")
    private String notetext;

    @Column(name="lastupdated")
    private Date lastupdated;

    public String getNotetext() {
        return notetext;
    }

    public void setNotetext(String notetext) {
        this.notetext = notetext;
    }

    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

