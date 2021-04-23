package ee.bcs.valiit.solution.hibernate;

import javax.persistence.*;

//@Entity
//@Table(name = "book")
public class MyBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private  String description;
    private String name;
    private int authorId;

    //HibernateController ja repository ja service vaja ka teha,
    // et saada andmeid kätte
}
