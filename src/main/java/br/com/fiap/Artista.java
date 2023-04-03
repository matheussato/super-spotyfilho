package br.com.fiap;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "TB_ARTISTA")
public class Artista {

    @Id
    @GeneratedValue(generator = "SQ_ARTISTA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_ARTISTA", sequenceName = "SQ_ARTISTA")

    @Column(name = "ID_ARTISTA")
    private long id;

    @Column(name = "NM_ARTISTA")
    private String nome;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "TB_MUSICA_ARTISTA",
            joinColumns = @JoinColumn(name = "ID_ARTISTA",
                    foreignKey = @ForeignKey(name = "FK_MA_ARTISTA", value = ConstraintMode.CONSTRAINT)
            ),
            inverseJoinColumns = @JoinColumn(name = "ID_MUSICA",
                    foreignKey = @ForeignKey(name = "FK_MA_MUSICA", value = ConstraintMode.CONSTRAINT)
            )
    )

    private Set<Musica> musicas = new HashSet<>();

    public Artista() {
    }

    public Artista(long id, String nome, Set<Musica> musicas) {
        this.id = id;
        this.nome = nome;
        this.musicas = musicas;
    }


    public Artista addMusica(Musica m) {
        this.musicas.add(m);
        m.getAstistas().add(this);
        return this;
    }

    public Artista removeMusica(Musica m) {
        this.musicas.remove(m);
        m.getAstistas().remove(this);
        return this;
    }

    public Artista removeMusicas() {
        Iterator<Musica> interator = this.musicas.iterator();

        while (interator.hasNext()) {
            Musica m = interator.next();
            m.getAstistas().remove(this);
            interator.remove();
        }
        return this;
    }


    public long getId() {
        return id;
    }

    public Artista setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Artista setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}

