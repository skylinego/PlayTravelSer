package models;

import play.data.validation.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Company entity managed by JPA
 */
@Entity 
public class Client {

    @Id
    public Long id;

    public String secret;

    public String token;

    @Column (name="expiration")
    private Timestamp expiration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getExpiration(){
        return expiration;
    }

    public String getSecret(){
        return secret;
    }

    public void setSecret(String secret){
        this.secret = secret;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public void setExpiration(Timestamp expiration) {
        this.expiration = expiration;
    }

    public static Client findById(Long id) {
        return JPA.em().find(Client.class, id);
    }

    public void save() {
        EntityManager em = JPA.em();

        em.getTransaction().begin();
        em.persist(this);
        em.getTransaction().commit();
    }

}

