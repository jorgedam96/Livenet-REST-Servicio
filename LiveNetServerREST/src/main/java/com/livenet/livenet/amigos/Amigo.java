package com.livenet.livenet.amigos;


import javax.persistence.*;

@Entity
@Table(name = "amigos")
public class Amigo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "alias_a")
    private String alias1;

    @Column(name = "alias_b")
    private String alias2;

    public Amigo(){}

    public Amigo(String alias1, String alias2) {
        this.alias1 = alias1;
        this.alias2 = alias2;

    }

    public String getAlias1() {
        return alias1;
    }

    public void setAlias1(String alias1) {
        this.alias1 = alias1;
    }

    public String getAlias2() {
        return alias2;
    }

    public void setAlias2(String alias2) {
        this.alias2 = alias2;
    }

    @Override
    public String toString() {
        return "Amigo{" +
                "alias1='" + alias1 + '\'' +
                ", alias2='" + alias2 + '\'' +
                '}';
    }
}
