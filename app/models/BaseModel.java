package models;

/**
 * Created by dliu15 on 2/19/18.
 */
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.sql.Timestamp;
import play.db.jpa.*;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class BaseModel implements Serializable {

    /**
     * Determines if a de-serialized file is compatible with this class.
     * Included here as a reminder of its importance.
     */
    //private static final long serialVersionUID = 7526471155622787654L;
    private   static final long   serialVersionUID = 1L;
    /////////////////////
    /// public members //
    /////////////////////
    @Id
    @GeneratedValue
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //@CreatedTimestamp
    @Column (name="createdAt")
    protected Timestamp created_ts;

    //@UpdatedTimestamp
    @Column (name="updatedAt")
    protected Timestamp updated_ts;

    public Timestamp getCreatedTs(){
        return created_ts;
    }

    public void setCreated_ts(Timestamp created_ts) {
        this.created_ts = created_ts;
    }

    public Timestamp getUpdatedTs(){
        return updated_ts;
    }

    public void setUpdated_ts(Timestamp updated_ts) {
        this.updated_ts = updated_ts;
    }

}
