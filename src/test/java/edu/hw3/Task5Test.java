package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    void contactsSortAscendingCorrectResult() {
        final Object[] EXPECTED_RESULT = new Object[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"};

        var contacts = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        var result = Task5.parseContacts(contacts, "ASC");

        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }

    @Test
    void contactsSortDescendingCorrectResult() {
        final Object[] EXPECTED_RESULT = new Object[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"};

        var contacts = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        var result = Task5.parseContacts(contacts, "DESC");

        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }

    @Test
    void contactsWithoutLastNamesSortAscendingCorrectResult() {
        final Object[] EXPECTED_RESULT = new Object[] {"David", "John", "Rene", "Thomas"};

        var contacts = new String[] {"John", "Thomas", "David", "Rene"};
        var result = Task5.parseContacts(contacts, "ASC");

        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }

    @Test
    void contactsWithoutLastNamesSortDescendingCorrectResult() {
        final Object[] EXPECTED_RESULT = new Object[] {"Paul", "Leonhard", "Carl"};

        var contacts = new String[] {"Paul", "Leonhard", "Carl"};
        var result = Task5.parseContacts(contacts, "DESC");

        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }
}
