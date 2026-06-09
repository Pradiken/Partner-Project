import java.util.Scanner;

public class MyProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PostManager manager = new PostManager();

        manager.loadPosts();

        boolean running = true;

        while (running) {
            System.out.println("\n===== JOB EXPERIENCE BOARD =====");
            System.out.println("1. Create a Post");
            System.out.println("2. View All Posts");
            System.out.println("3. Add a Comment");
            System.out.println("4. Delete a Post");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = getNumber(input);

            if (choice == 1) {
                System.out.print("Your Name: ");
                String name = input.nextLine();

                System.out.print("Job Title: ");
                String jobTitle = input.nextLine();

                System.out.print("Company/Workplace: ");
                String company = input.nextLine();

                System.out.print("Workload, like light, medium, or heavy: ");
                String workload = input.nextLine();

                System.out.print("Describe the Workspace: ");
                String workspace = input.nextLine();

                System.out.print("Describe your Experience: ");
                String experience = input.nextLine();

                if (name.equals("") || jobTitle.equals("") || company.equals("") ||
                    workload.equals("") || workspace.equals("") || experience.equals("")) {
                    System.out.println("You cannot leave any answers blank. Please try again.");
                } else {
                    Post newPost = new Post(name, jobTitle, company, workload, workspace, experience);
                    manager.addPost(newPost);
                    System.out.println("Post added successfully.");
                }
            }

            else if (choice == 2) {
                manager.showPosts();
            }

            else if (choice == 3) {
                manager.showPosts();

                if (manager.getPostCount() > 0) {
                    System.out.print("Enter the post number you want to comment on: ");
                    int postNumber = getNumber(input);

                    System.out.print("Write your comment: ");
                    String comment = input.nextLine();

                    if (comment.equals("")) {
                        System.out.println("Comment cannot be blank.");
                    } else {
                        manager.addComment(postNumber - 1, comment);
                    }
                }
            }

            else if (choice == 4) {
                manager.showPosts();

                if (manager.getPostCount() > 0) {
                    System.out.print("Enter the post number you want to delete: ");
                    int postNumber = getNumber(input);

                    manager.deletePost(postNumber - 1);
                }
            }

            else if (choice == 5) {
                manager.savePosts();
                running = false;
                System.out.println("Program closed. Posts were saved.");
            }

            else {
                System.out.println("Invalid choice. Please choose a number from 1 to 5.");
            }
        }

        input.close();
    }

    public static int getNumber(Scanner input) {
        while (true) {
            String answer = input.nextLine();

            try {
                int number = Integer.parseInt(answer);
                return number;
            } catch (Exception e) {
                System.out.print("Invalid input. Please type a number: ");
            }
        }
    }
}
