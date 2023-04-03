package br.com.fiap;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_MUSICA")
public class Musica {

    @Id
    @GeneratedValue(generator = "SQ_MUSICA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_MUSICA", sequenceName = "SQ_MUSICA")

    @Column(name = "ID_MUSICA")
    private long id;

    @Column(name = "NM-MUSICA")
    private String nome;

    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO",
            foreignKey = @ForeignKey(name = "FK_MUSICA_ESTILO", value = ConstraintMode.CONSTRAINT)
    )
    private Estilo estilo;

    @ManyToMany(mappedBy = "musicas")
    @OrderBy("nome DESC")
    private Set<Artista> astistas = new HashSet<>();


    public Musica() {
    }

    public Musica(long id, String nome, Set<Artista> astistas, Estilo estilo) {
        this.id = id;
        this.nome = nome;
        this.astistas = astistas;
        this.estilo = estilo;
    }

    public long getId() {
        return id;
    }

    public Musica setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Musica setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Set<Artista> getAstistas() {
        return astistas;
    }

    public Musica setAstistas(Set<Artista> astistas) {
        this.astistas = astistas;
        return this;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public Musica setEstilo(Estilo estilo) {
        this.estilo = estilo;
        return this;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", astistas=" + astistas +
                ", estilo=" + estilo +
                '}';
    }
}
