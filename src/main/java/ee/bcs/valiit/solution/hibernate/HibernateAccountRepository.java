package ee.bcs.valiit.solution.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HibernateAccountRepository extends JpaRepository<Account, String> {
    //String on andmetüüp, kuna primaryKey on accountNr mille tüüp on String

}
