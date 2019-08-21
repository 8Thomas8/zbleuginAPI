package com.apsidepoei.projetpoeitest.database.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.apsidepoei.projetpoei.database.DbManager;
import com.apsidepoei.projetpoei.entities.Address;
import com.apsidepoei.projetpoei.entities.Assessment;
import com.apsidepoei.projetpoei.entities.Degree;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class AssessmentDaoUpdateTest {


    private static final String CHANGED_CATEGORY = "toto";
    private List<Assessment> assessments = new ArrayList<Assessment>();

    @Before
    /** setup for test */
    public void setupTests() throws SQLException, ParseException {
        DbManager.getInstance().getAssessmentDao().drop();
        DbManager.getInstance().getAssessmentDao().create();

        assessments.clear();
        assessments.add(new Assessment("Riri", new SimpleDateFormat("yyyy/MM/dd").parse("1999/12/31")));
        assessments.add(new Assessment("Fifi", new SimpleDateFormat("yyyy/MM/dd").parse("1999/12/31")));
        assessments.add(new Assessment("Loulou", new SimpleDateFormat("yyyy/MM/dd").parse("1999/12/31")));

        for (Assessment assessment : assessments) {
            DbManager.getInstance().getAssessmentDao().insert(assessment);
        }
    }
    /**
    *
    * @throws SQLException
    * Test data updated is the same
    */
    @Test
    public void simpleUpdateCompare1() throws SQLException {
        Assessment assessment = assessments.get(0);
        assessment.setCategory(CHANGED_CATEGORY);

        Assessment dbAssessment = (Assessment)DbManager.getInstance().getAssessmentDao().select(1);
        DbManager.getInstance().getAssessmentDao().update(assessment);
        Assessment dbAssessmentUpdated = (Assessment)DbManager.getInstance().getAssessmentDao().select(1);

        assertTrue(dbAssessment.getId() == dbAssessmentUpdated.getId() && !dbAssessment.getCategory().equals(dbAssessmentUpdated.getCategory()) && dbAssessmentUpdated.getCategory().equals(CHANGED_CATEGORY));
    }
    /**
    *
    * @throws SQLException
    * Test data updated is the same
    */
    @Test
    public void simpleUpdateCompare2() throws SQLException {
        Assessment assessment = assessments.get(0);
        assessment.setCategory(CHANGED_CATEGORY);

        DbManager.getInstance().getAssessmentDao().update(assessment);
        Assessment dbAssessmentUpdated = (Assessment)DbManager.getInstance().getAssessmentDao().select(1);

        assertTrue(assessment.getId() == dbAssessmentUpdated.getId() && assessment.getCategory().equals(dbAssessmentUpdated.getCategory()));
    }
    /**
    *
    * @throws SQLException
    * Test data updated is the same
    */
    @Test
    public void simpleUpdateCompare3() {
        Assessment assessment = assessments.get(0);
        Assessment dbAssessment = (Assessment)DbManager.getInstance().getAssessmentDao().select(1);

        assertTrue(assessment.getId() == dbAssessment.getId() && assessment.getCategory().equals(dbAssessment.getCategory()));
    }
    /**
    *
    * @throws SQLException
    * Test if data is truncated when update is too long
    */
    @Test(expected = MysqlDataTruncation.class)
    public void updateMaxValExtended() throws SQLException {
        Assessment assessment = assessments.get(0);

        StringBuilder data = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            data.append("x");
        }
        assessment.setCategory(data.toString());

        DbManager.getInstance().getAssessmentDao().update(assessment);
    }

    /**
    *
    * @throws SQLException
    * Test if data update is too long
    */
    @Test
    public void updateMaxValOK() throws SQLException {
        Assessment assessment = assessments.get(0);

        StringBuilder data = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            data.append("x");
        }
        assessment.setCategory(data.toString());

        DbManager.getInstance().getAssessmentDao().update(assessment);
    }
    /**
    *
    * @throws SQLException
    * Test if data update is empty
    */
    @Test
    public void updateMinValOK() throws SQLException {
        Assessment assessment = assessments.get(0);
        assessment.setCategory("");

        DbManager.getInstance().getAssessmentDao().update(assessment);
    }
    /**
    *
    * @throws SQLException
    * Test if update with null
    */
    @Test(expected = MySQLIntegrityConstraintViolationException.class)
    public void updateNullValKO() throws SQLException {
        Assessment assessment = assessments.get(0);
        assessment.setCategory(null);

        DbManager.getInstance().getAssessmentDao().update(assessment);
    }

    /**
    *
    * @throws SQLException
    * Test if  update with wrong id
    */
    @Test
    public void updateWrongId() throws SQLException {
        Assessment assessment = assessments.get(0);
        assessment.setId(4);
        assessment.setCategory(CHANGED_CATEGORY);

        assertEquals(new Integer(0), DbManager.getInstance().getAssessmentDao().update(assessment));
    }
    /**
    *
    * @throws SQLException
    * Test if data is update with good id
    */
    @Test
    public void updateGoodId() throws SQLException {
        Assessment assessment = assessments.get(0);
        assessment.setCategory(CHANGED_CATEGORY);

        assertEquals(new Integer(1), DbManager.getInstance().getAssessmentDao().update(assessment));
    }
    /**
    *
    * @throws SQLException
    * Test if update category with good id
    */
    @Test
    public void updateCategoryGoodId() throws SQLException {
        Assessment assessment = assessments.get(0);
        assessment.setCategory(CHANGED_CATEGORY);
        assertEquals(new Integer(1), DbManager.getInstance().getAssessmentDao().update(assessment));
    }

    /**
    *
    * @throws SQLException
    * Test if data is truncated when update is too long
    */
   @Test(expected = MysqlDataTruncation.class)
   public void updateMaxValExtendedAssessment() throws SQLException {
       Assessment assessment = assessments.get(0);

       StringBuilder data = new StringBuilder();
       for (int i = 0; i < 50; i++) {
           data.append("x");
       }
       assessment.setAssessment(data.toString());

       DbManager.getInstance().getAssessmentDao().update(assessment);
   }
}



