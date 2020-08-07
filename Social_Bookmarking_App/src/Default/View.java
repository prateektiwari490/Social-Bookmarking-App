package Default;

import java.util.List;

import constants.KidFriendlyStatus;
import constants.UserType;
import controllers.BookmarkController;
import entities.Bookmark;
import entities.User;
import partner.Shareable;

public class View {

	public static void browse(User user, List<List<Bookmark>> bookmarks) {
		System.out.println("\n" + user.getEmail() + " is Browsing items... ");
		// int bookmarkCount = 0;
		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				// Bookmarking !!
				// if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
				boolean isBookmarked = getBookmarkDecision(bookmark);
				if (isBookmarked) {
					BookmarkController.getInstance().saveUserBookmark(user,
							bookmark);

					System.out.println(" New Item Bookmarked --  " + bookmark);
				}
				// }
				if (user.getUserType().equals(UserType.EDITOR)
						|| user.getUserType().equals(UserType.CHIEF_EDITOR)) {

					// Mark as kid-Friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus()
									.equals(KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(
								bookmark);
						if (!kidFriendlyStatus
								.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance()
									.setKidFriendlyStatus(user,
											kidFriendlyStatus, bookmark);
						}
					}

					// Sharing !!
					if (bookmark.getKidFriendlyStatus()
							.equals(KidFriendlyStatus.APPROVED)
							&& bookmark instanceof Shareable) {
						boolean isShared = getSherDecision();
						if (isShared) {
							BookmarkController.getInstance().share(user,
									bookmark);
						}
					}

				}
			}
		}
	}

	// TODO: Below mathods simulate user input after IO, we take input via
	// console.
	private static boolean getSherDecision() {
		return Math.random() < 0.5 ? true : false;

	}

	private static KidFriendlyStatus getKidFriendlyStatusDecision(
			Bookmark bookmark) {
		return Math.random() < 0.4
				? KidFriendlyStatus.APPROVED
				: (Math.random() >= 0.4 && Math.random() < 0.8)
						? KidFriendlyStatus.REJECTED
						: KidFriendlyStatus.UNKNOWN;
	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {

		return Math.random() < 0.5 ? true : false;
	}

	/*
	 * public static void bookmark(User user, Bookmark[][] bookmarks) {
	 * System.out.println("\n" + user.getEmail() + " is Bookmarking "); for(int
	 * i = 0;i<DataStore.USER_BOOKMARK_LIMIT;i++) { int typeOffset =
	 * (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT); int bookmarkOffset
	 * = (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
	 * 
	 * Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
	 * 
	 * BookmarkController.getInstance().saveUserBookmark(user, bookmark);
	 * 
	 * System.out.println(bookmark); } }
	 */
}
