package fr.joand.mdf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MainTag {
    public static void main(String[] argv) throws Exception {

        Scanner sc = new Scanner(System.in);
        int nbTotalTag = Integer.parseInt(sc.nextLine());

        List<Tag> allTags = new ArrayList<>();
        for (int nbTag = 0; nbTag < nbTotalTag; nbTag++) {
            String tagName = sc.nextLine();
            Tag tag = new Tag(tagName);
            if (allTags.contains(tag)) {
                IsoContestBase.localEcho("existing tag : " + tag.getName() + " " + tag.getOccurence());
                int index = allTags.indexOf(tag);
                tag = allTags.get(index);
                tag.increment();
            } else {
                IsoContestBase.localEcho("create tag : " + tag.getName());
                allTags.add(tag);
            }
        }

        allTags.stream()
                .sorted((o1, o2) -> o2.getOccurence() - o1.getOccurence())
                .limit(5)
                .forEach(tag -> System.out.println(tag.getName() + " " + tag.getOccurence()));
    }
}

class Tag {
    final String name;
    int occurence = 1;

    public Tag(String name) {
        this.name = name;
    }

    void increment() {
        occurence++;
    }

    public String getName() {
        return name;
    }

    public int getOccurence() {
        return occurence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}