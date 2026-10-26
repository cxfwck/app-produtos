package cotiinformatica.services;

import cotiinformatica.entities.Categoria;
import cotiinformatica.interfaces.CategoriaService;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CategoriaServiceImpl implements CategoriaService {

    private final String arquivo = "categorias.txt";

    @Override
    public void cadastrar(Categoria categoria) {

        categoria.setId(UUID.randomUUID());

        var scanner = new Scanner(System.in);

        System.out.println("\nCADASTRO DE CATEGORIA:\n");

        System.out.print("INFORME O NOME........: ");
        categoria.setNome(scanner.nextLine());

        System.out.print("INFORME A DESCRIÇÃO...: ");
        categoria.setDescricao(scanner.nextLine());

        try (var printWriter = new PrintWriter(new FileWriter(arquivo, true))) {
            printWriter.write(categoria.getId() + "\n");
            printWriter.write(categoria.getNome() + "\n");
            printWriter.write(categoria.getDescricao() + "\n");
            printWriter.write("...");

            System.out.println("\nDADOS GRAVADOS COM SUCESSO!");
        }
        catch(Exception e) {
            System.out.println("\nFALHA AO GRAVAR ARQUIVO: " + e.getMessage());
        }
    }

    @Override
    public List<Categoria> consultar() {

        System.out.println("\nCONSULTA DE CATEGORIAS:\n");

        var categorias = new ArrayList<Categoria>();

        try (var scanner = new Scanner(new File(arquivo))) {

            while(scanner.hasNextLine()) {

                var linha = scanner.nextLine().trim();

                if (linha.startsWith("...")) {
                    linha = linha.substring(3).trim();
                }

                if (linha.isEmpty()) {
                    continue;
                }

                var categoria = new Categoria();

                categoria.setId(UUID.fromString(linha));
                categoria.setNome(scanner.nextLine());
                categoria.setDescricao(scanner.nextLine());

                categorias.add(categoria);
            }
        }
        catch(Exception e) {
            System.out.println("\nFALHA AO LER ARQUIVO: " + e.getMessage());
        }

        return categorias;
    }
}
