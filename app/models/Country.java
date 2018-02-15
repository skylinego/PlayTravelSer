package models;

import play.data.validation.*;
import play.db.jpa.*;

import javax.persistence.*;

/**
 * Company entity managed by JPA
 */
@Entity 
public class Country {

    @Id
    public Long id;

    public String code;

    public String country;

    public String codeISO3;

    public String capital;

    public String countryCode;

    public String languageCodes;

    public String zipCodeFormat;

    public String zipCodePattern;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn (name="continentID")
    public Continent continent;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn (name="currencyID")
    public Currency currency;
    
    public static Country findById(Long id) {
        return JPA.em().find(Country.class, id);
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

