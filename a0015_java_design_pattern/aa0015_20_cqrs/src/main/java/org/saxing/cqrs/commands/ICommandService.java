package org.saxing.cqrs.commands;

/**
 * This interface represents the commands of the CQRS pattern
 *
 * @author saxing 2018/12/1 21:23
 */
public interface ICommandService {

    void authorCreated(String username, String name, String email);

    void bookAddedToAuthor(String title, double price, String username);

    void authorNameUpdated(String username, String name);

    void authorUsernameUpdated(String oldUsername, String newUsername);

    void authorEmailUpdated(String username, String email);

    void bookTitleUpdated(String oldTitle, String newTitle);

    void bookPriceUpdated(String title, double price);

}
