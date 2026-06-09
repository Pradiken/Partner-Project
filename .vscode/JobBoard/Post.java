import java.util.ArrayList;

public class Post {
    private String name;
    private String jobTitle;
    private String company;
    private String workload;
    private String workspace;
    private String experience;
    private ArrayList<String> comments;

    public Post(String name, String jobTitle, String company, String workload, String workspace, String experience) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.company = company;
        this.workload = workload;
        this.workspace = workspace;
        this.experience = experience;
        this.comments = new ArrayList<String>();
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public String toString() {
        String text = "";

        text += "Name: " + name + "\n";
        text += "Job Title: " + jobTitle + "\n";
        text += "Company: " + company + "\n";
        text += "Workload: " + workload + "\n";
        text += "Workspace: " + workspace + "\n";
        text += "Experience: " + experience + "\n";

        text += "Comments:\n";

        if (comments.size() == 0) {
            text += "No comments yet.\n";
        } else {
            for (int i = 0; i < comments.size(); i++) {
                text += "- " + comments.get(i) + "\n";
            }
        }

        return text;
    }

    public String saveFormat() {
        String text = "";

        text += name + "\n";
        text += jobTitle + "\n";
        text += company + "\n";
        text += workload + "\n";
        text += workspace + "\n";
        text += experience + "\n";

        for (int i = 0; i < comments.size(); i++) {
            text += "COMMENT:" + comments.get(i) + "\n";
        }

        text += "ENDPOST";

        return text;
    }

    public static Post fromFileData(ArrayList<String> lines) {
        String name = lines.get(0);
        String jobTitle = lines.get(1);
        String company = lines.get(2);
        String workload = lines.get(3);
        String workspace = lines.get(4);
        String experience = lines.get(5);

        Post post = new Post(name, jobTitle, company, workload, workspace, experience);

        for (int i = 6; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.startsWith("COMMENT:")) {
                String comment = line.substring(8);
                post.addComment(comment);
            }
        }

        return post;
    }
}
