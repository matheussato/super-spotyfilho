package br.com.fiap;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_ESTILO")
public class Estilo {

    @Id
    @GeneratedValue(generator = "SQ_ESTILO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_ESTILO", sequenceName = "SQ_ESTILO")

    @Column(name = "ID_ESTILO")
    private long id;

    @Column(name = "NM_ESTILO")
    private String nome;


    public long getId() {
        return id;
    }

    public Estilo setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Estilo setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Estilo(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Estilo() {
    }

    @Override
    public String toString() {
        return "Estilo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
