package br.com.fiap;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

        save(manager);

        manager.find(Musica.class, 1L);

        String jqpl = "FROM Musica";
        List<Musica> resultList = manager.createQuery(jqpl).getResultList();
        resultList.forEach(System.out::println);

    }

    private static void save(EntityManager manager) {
        var estilo = new Estilo(0, "RAP-TRAP");

        var musica = new Musica();
        musica.setEstilo(estilo).setNome("Licor 43");

        var vocal = new Artista();
        vocal.setNome("KayBlack").addMusica(musica);

        var baixo = new Artista();
        baixo.setNome("Não tem").addMusica(musica);

        var guitarra = new Artista();
        guitarra.setNome("Não tem").addMusica(musica);

        manager.getTransaction().begin();

        Arrays.asList(vocal, baixo, guitarra).forEach(
                manager::persist
        );
        manager.persist(estilo);
        manager.persist(musica);
        manager.getTransaction().commit();
    }
}