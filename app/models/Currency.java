package models;

import play.data.validation.*;
import play.db.jpa.*;

import javax.persistence.*;

/**
 * Company entity managed by JPA
 */
@Entity 
public class Currency {

    @Id
    public Long id;

    public String code;

    public String currency;
    
    public static Currency findById(Long id) {
        return JPA.em().find(Currency.class, id);
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

