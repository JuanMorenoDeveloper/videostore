import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.summingInt;

public class Statement {

    private final String name;
    private final List<StatementLineItem> items;

    public Statement(String name, List<StatementLineItem> items) {
        this.name = name;
        this.items = items;
    }

    public String render() {
        List<String> lines = new ArrayList<>();

        lines.add("Rental Record for " + name);

        items.forEach(i -> lines.add("\t" + i.title + "\t" + String.valueOf(i.price)));

        lines.add("You owed " + String.valueOf(items.stream().collect(summingDouble(i -> i.price))));
        lines.add("You earned " + String.valueOf(items.stream().collect(summingInt(i -> i.points))) + " frequent renter points");

        return lines.stream().collect(joining("\n", "", "\n"));
    }
}
