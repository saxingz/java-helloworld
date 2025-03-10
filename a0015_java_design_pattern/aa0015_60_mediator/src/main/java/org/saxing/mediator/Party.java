package org.saxing.mediator;

/**
 * party
 *
 * @author saxing 2019/4/18 13:07
 */
public interface Party {

    void addMember(PartyMember member);

    void act(PartyMember actor, Action action);

}
