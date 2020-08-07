package Default;

import java.util.List;

import backgroundJobs.WebPageDownloaderTask;

/*
 * Object Oriented Design and coding Convention
 * Design Pattern : MVC (Model View Controller) and Singleton
 * TDD (Test Driven Development)
 * 
 *  View (JSPs) <--> Controller (Servlet) <--> {Model (Java Classes) [Manager] <-> [Daos] "Business Logic"} <--> DB (Database)
 *  
 *  Benefits : Seperation of Business Logic from Presentation  Layer
 */

import entities.Bookmark;
import entities.User;
import managers.BookmarkManager;
import managers.UserManager;

public class Launch {
	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;

	private static void loadData() {
		System.out.println("1. Loading Data...");
		DataStore.loadData();

		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();

		// System.out.println("Printing data...");
		// printUserData();
		// printBookmarkData();

	}

	private static void printBookmarkData() {
		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark + " ");
			}
		}

	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user + " ");
		}
	}

	private static void start() {
		// System.out.println("\n2. Bookmarking...");
		for (User user : users) {
			View.browse(user, bookmarks);
		}
	}

	public static void main(String[] args) {
		loadData();
		start();
		
		// Backgroung Jobs
		runDownloaderJob();
	}

	private static void runDownloaderJob() {
		WebPageDownloaderTask task = new WebPageDownloaderTask(true);
		(new Thread(task)).start();
	}

}
