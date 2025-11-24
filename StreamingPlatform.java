package examen2024;
import java.util.*;

public class StreamingPlatform {
	private Content[] contents;
    private User[] users;
    private int nbContents = 0;
    private int nbUsers = 0;

    public StreamingPlatform(int capacityContents, int capacityUsers) {
        contents = new Content[capacityContents];
        users = new User[capacityUsers];
    }

    public void addContent(Content content) {
        if (nbContents < contents.length) {
            contents[nbContents++] = content;
        } else {
            System.out.println("Capacité contenus atteinte.");
        }
    }

    public void addUser(User user) {
        if (nbUsers < users.length) {
            users[nbUsers++] = user;
        } else {
            System.out.println("Capacité utilisateurs atteinte.");
        }
    }

    public Content findContent(String title) throws ContentNotFoundException {
        for (int i = 0; i < nbContents; i++) {
            if (contents[i].getTitle().equalsIgnoreCase(title)) {
                return contents[i];
            }
        }
        throw new ContentNotFoundException("Contenu introuvable: " + title);
    }

    public void playContent(User user, String title) throws ContentNotFoundException, AccessDeniedException {
        Content c = findContent(title);
        if (!user.canPlay(c)) {
            throw new AccessDeniedException("Accès refusé: " + user.getName() + " n'a pas le droit de voir " + title);
        }
        c.play();
        user.addWatchedContent(c);
    }

    public Content[] getRecommendations(User user) {
        Set<String> watchedGenres = new HashSet<>(Arrays.asList(user.getWatchedGenres()));
        List<Content> recs = new ArrayList<>();
        for (int i = 0; i < nbContents && recs.size() < 10; i++) {
            Content c = contents[i];
            if (user.hasWatched(c)) continue;
            if (watchedGenres.contains(c.getGenre())) {
                recs.add(c);
            }
        }
        return recs.toArray(new Content[0]);
    }
}
