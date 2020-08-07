package entities;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import constants.BookGenre;
import managers.BookmarkManager;

class BookTest {

	@Test
	void testIsKidFriendlyEligible() {
		// Test 1
		Book book = BookmarkManager.getInstance().createBook(4000,
				"Walden", 1854, "Wilder Publications",
				new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY, 4.3);
		boolean isKidFriendlyEligible = book.isKidFriendlyEligible();
		assertFalse("for PHILOSOPHY Genre - isKidFriendlyEligible() should return false",isKidFriendlyEligible);
		
		// Test 2
		book = BookmarkManager.getInstance().createBook(4000,
				"Walden", 1854, "Wilder Publications",
				new String[]{"Henry David Thoreau"}, BookGenre.SELF_HELP, 4.3);
		isKidFriendlyEligible = book.isKidFriendlyEligible();
		assertFalse("for SELF_HELP Genre - isKidFriendlyEligible() should return false",isKidFriendlyEligible);
	}

}
