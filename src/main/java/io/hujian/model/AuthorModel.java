package io.hujian.model;

import java.util.List;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the model of author
 */
public class AuthorModel {

    private int authorId; // the author id
    private int authorAge; // the age
    private int authorLevel; // the level
    private String authorAddr; // the address

    private List<Integer> friends; // the friends of the author

    /**
     * for debug
     * @return the author info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("authorId:").append(authorId).append(",authorAge:").append(authorAge).append(",").append("authorLevel:").append(authorLevel).append(",author Address:").append(authorAddr).append(",").append("friends:");
        for (Integer friend : friends) {
            sb.append(friend).append(" ");
        }

        return sb.toString();
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getAuthorAge() {
        return authorAge;
    }

    public void setAuthorAge(int authorAge) {
        this.authorAge = authorAge;
    }

    public int getAuthorLevel() {
        return authorLevel;
    }

    public void setAuthorLevel(int authorLevel) {
        this.authorLevel = authorLevel;
    }

    public String getAuthorAddr() {
        return authorAddr;
    }

    public void setAuthorAddr(String authorAddr) {
        this.authorAddr = authorAddr;
    }

    public List<Integer> getFriends() {
        return friends;
    }

    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }
}
