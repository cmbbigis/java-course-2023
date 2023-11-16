package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    void contactsSortAscending() {
        final Object[] expectedResult = new Object[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"};

        var contacts = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        var result = Task5.parseContacts(contacts, "ASC");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void contactsSortDescending() {
        final Object[] expectedResult = new Object[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"};

        var contacts = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        var result = Task5.parseContacts(contacts, "DESC");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void contactsWithoutLastNamesSortAscending() {
        final Object[] expectedResult = new Object[] {"David", "John", "Rene", "Thomas"};

        var contacts = new String[] {"John", "Thomas", "David", "Rene"};
        var result = Task5.parseContacts(contacts, "ASC");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void contactsWithoutLastNamesSortDescending() {
        final Object[] expectedResult = new Object[] {"Paul", "Leonhard", "Carl"};

        var contacts = new String[] {"Paul", "Leonhard", "Carl"};
        var result = Task5.parseContacts(contacts, "DESC");

        assertThat(result).isEqualTo(expectedResult);
    }
}
