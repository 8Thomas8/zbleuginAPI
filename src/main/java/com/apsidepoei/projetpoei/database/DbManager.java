package com.apsidepoei.projetpoei.database;

import com.apsidepoei.projetpoei.database.dao.AdressDao;
import com.apsidepoei.projetpoei.database.dao.DegreeDao;
import com.apsidepoei.projetpoei.database.dao.EntrepriseDao;
import com.apsidepoei.projetpoei.database.dao.MatiereDao;

public class DbManager {

    private AdressDao adressDao = new AdressDao();
    private DegreeDao degreeDao = new DegreeDao();
    private EntrepriseDao entrepriseDao = new EntrepriseDao();
    private MatiereDao matiereDao = new MatiereDao();


    /** Constructeur privé */
    private DbManager() {
    }

    /** Instance unique non préinitialisée */
    private static DbManager INSTANCE = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static DbManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DbManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DbManager();
                }
            }
        }
        return INSTANCE;
    }

    // GETTER/SETTER
    /**
     * @return the adressDao
     */
    public AdressDao getAdressDao() {
        return adressDao;
    }

    /**
     * @return the degreeDao
     */
    public DegreeDao getDegreeDao() {
        return degreeDao;
    }

    /**
     * @return the entrepriseDao
     */
    public EntrepriseDao getEntrepriseDao() {
        return entrepriseDao;
    }

    /**
     * @return the matiereDao
     */
    public MatiereDao getMatiereDao() {
        return matiereDao;
    }

}
