package cz.cvut.fel.ear.lingo.dao;

import cz.cvut.fel.ear.lingo.LingoApplication;
import cz.cvut.fel.ear.lingo.repo.RepoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest
@ComponentScan(basePackageClasses = LingoApplication.class)
public class RepoDaoTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private RepoDao dao;
}
