package models;

import play.data.validation.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Company entity managed by JPA
 */
@Entity 
public class Continent {

    @Id
    public Long id;

    public String code;

    public String continent;
    
    public static Continent findById(Long id) {
        return JPA.em().find(Continent.class, id);
    }

    /*public static Map<String,String> options() {
        @SuppressWarnings("unchecked")
				List<Continent> companies = JPA.em().createQuery("from Company order by name").getResultList();
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Continent c: companies) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }*/

}

