package examen2024;

public class User {
    private String name;
    private boolean isSubscribed;
    private Content[] watchedContent;
    private int nbWatched;
    public static final int MAX_WATCHED = 100;

    public User(String name, boolean isSubscribed) {
        this.name = name;
        this.isSubscribed = isSubscribed;
        watchedContent = new Content[MAX_WATCHED];
        nbWatched = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public boolean canPlay(Content content) {
        return isSubscribed || content.isfree;
    }

    public void addWatchedContent(Content content) {
        if (nbWatched < MAX_WATCHED) {
            watchedContent[nbWatched++] = content;
        }
    }

    public String[] getWatchedGenres() {
        String[] genres = new String[nbWatched];
        int count = 0;

        for (int i = 0; i < nbWatched; i++) {
            String g = watchedContent[i].getGenre();
            boolean existe = false;

            for (int j = 0; j < count; j++) {
                if (genres[j].equals(g)) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                genres[count++] = g;
            }
        }

        String[] resultat = new String[count];
        for (int i = 0; i < count; i++)
            resultat[i] = genres[i];

        return resultat;
    }

    public boolean hasWatched(Content content) {
        for (int i = 0; i < nbWatched; i++) {
            if (watchedContent[i].getTitle().equalsIgnoreCase(content.getTitle())) {
                return true;
            }
        }
        return false;
    }
}
