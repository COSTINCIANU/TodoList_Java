package main;

import models.Account;
import models.Task;
import models.Category;
import repository.AccountRepository;
import repository.TaskRepository;
import repository.CategoryRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Créer les instances du repository
        AccountRepository accountRepo = new AccountRepository();
        TaskRepository taskRepo = new TaskRepository();
        CategoryRepository categoryRepo = new CategoryRepository();

        System.out.print("Nom de la catégorie : ");
        String categoryName = scanner.nextLine();

        // Obtenir l'ID à partir de la base de données ou utiliser une valeur temporaire,
        // par exemple un ID fictif.
        int categoryId = 1;


        // Créez la catégorie avec l'ID et le nom
        Category category = new Category(categoryId, categoryName);
        // Ajouter la catégorie à la base de données
        categoryRepo.add(category);


        System.out.println("Ajout d'une nouvelle tâche");
        System.out.print("Titre : ");
        String title = scanner.nextLine();
        System.out.print("Description : ");
        String description = scanner.nextLine();
        System.out.print("ID du compte associé : ");
        int accountId = Integer.parseInt(scanner.nextLine());

        Task newTask = new Task(title, description, false, accountId);
        taskRepo.add(newTask);

        // Ajouter un compte
        System.out.println("Ajouter un compte");
        System.out.print("Nom : ");
        String lastname = scanner.nextLine();
        System.out.print("Prénom : ");
        String firstname = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();
        Account account = new Account(lastname, firstname, email, password);

        // Ajouter le compte à la base de données
        accountRepo.add(account);


        // Ajouter une catégorie
        System.out.println("Ajouter une catégorie");
        System.out.print("Nom de la catégorie : ");
        // String categoryName = scanner.nextLine();
        // Category category = new Category(0, categoryName);
        categoryRepo.add(category);

        // Ajouter une tâche
        System.out.println("Ajouter une tâche");
        System.out.print("Titre de la tâche : ");
        String taskTitle = scanner.nextLine();
        System.out.print("Description de la tâche : ");
        String taskDescription = scanner.nextLine();
        Task task = new Task(taskTitle, taskDescription, false, account.getId());
        taskRepo.add(task);

        // Valider une tâche
        System.out.println("Valider une tâche");
        System.out.print("ID de la tâche à valider : ");
        int taskId = scanner.nextInt();
        taskRepo.validateTask(taskId);

        // Afficher toutes les tâches
        System.out.println("Liste des tâches :");
        for (Task t : taskRepo.findAll()) {
            System.out.println(t);
        }

    }
        scanner.close();
}
